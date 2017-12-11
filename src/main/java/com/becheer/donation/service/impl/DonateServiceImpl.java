package com.becheer.donation.service.impl;

import com.becheer.donation.dao.DntPaymentPlanMapper;
import com.becheer.donation.dao.PaymentPlanMapper;
import com.becheer.donation.model.AliIpDetail;
import com.becheer.donation.model.DntNoContractDonate;
import com.becheer.donation.model.DntPaymentPlan;
import com.becheer.donation.model.extension.donate.Donate;
import com.becheer.donation.model.extension.donate.DonateContract;
import com.becheer.donation.model.extension.payment.PaymentPlanExtension;
import com.becheer.donation.service.*;
import com.becheer.donation.utils.GenerateUtil;
import com.becheer.donation.utils.IPUtil;
import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Map;

@Service
public class DonateServiceImpl implements IDonateService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DonateServiceImpl.class);

    @Resource
    private IDntNoContractDonateService noContractDonateService;

    @Resource
    private IDntPaymentPlanService paymentPlanService;

    @Resource
    private IWxPayService payService;

    @Resource
    private IAliPayService aliPayService;

    @Resource
    private PaymentPlanMapper paymentPlanMapper;


    public Map<String, String> donate(Donate donate, String ip, String memberName) {
        Integer projectTypeId = donate.getProjectTypeId();
        Integer projectId = donate.getProjectId();
        Long memberId = donate.getMemberId();
        Integer amount = donate.getAmount();

        // 1.写入直接捐赠表 dnt_no_contract_donate
        DntNoContractDonate dntNoContractDonate = new DntNoContractDonate();

        // Integer projectTypeId;          // projectTypeId bigint null comment '项目类型标识【,.$(projectTypeName)@(dntProjectType)】',
        // Integer projectId;             // projectId bigint null comment '项目标识【,.!】',
        // String noContractDonateNo;        // noContractDonateNo varchar(20) null comment '直接捐赠编号【,.!】',
        // Integer memberId;        // memberId bigint null comment '会员标识【,.!】',
        // Integer amount;        // amount bigint default '0' null comment '捐赠金额【,.*】',
        // Integer donatedAmount;        // donatedAmount bigint default '0' null comment '已捐赠金额【,.!】',
        // Integer allocateAmount;        // allocateAmount bigint default '0' null comment '已批资金额【,.!】',
        // String remark;        // remark varchar(1000) null comment '备注【,.】',
        // Integer enable;        // enable int null comment '状态',
        // Date createTime;        // createTime datetime null comment '创建时间',
        // Integer createBy;        // createBy bigint null comment '创建人',
        // Date updateTime;        // updateTime datetime null comment '修改时间',
        // Integer updateBy;        // updateBy bigint null comment '修改人',
        // String ip;        // ip varchar(64) null comment 'ip',
        // Integer appropriationContractId;
        dntNoContractDonate.setProjectTypeId(projectTypeId);
        dntNoContractDonate.setProjectId(projectId);
        dntNoContractDonate.setNoContractDonateNo(GenerateUtil.genNoContractDonateNo());
        dntNoContractDonate.setMemberId(memberId);
        dntNoContractDonate.setAmount(amount);
        dntNoContractDonate.setDonatedAmount(0);
        dntNoContractDonate.setAllocateAmount(0);
        dntNoContractDonate.setEnable(1);
        dntNoContractDonate.setIp(ip);
        dntNoContractDonate.setCreateTime(new Date());

        //根据IP获取具体地址信息
        AliIpDetail aliIpDetail = IPUtil.getIpLocation("ip=" + ip);
        if (aliIpDetail != null) {
            dntNoContractDonate.setIpCountry(aliIpDetail.getCountry());
            dntNoContractDonate.setIpRegion(aliIpDetail.getRegion());
            dntNoContractDonate.setIpArea(aliIpDetail.getArea());
            dntNoContractDonate.setIpCity(aliIpDetail.getCity());
            dntNoContractDonate.setIsp(aliIpDetail.getIsp());
        }
        // TODO 增加容错处理
        noContractDonateService.insert(dntNoContractDonate);

        // 2.写入付款计划表 dnt_payment_plan
        DntPaymentPlan dntPaymentPlan = new DntPaymentPlan();


        String title = "捐赠了" + String.valueOf(amount / 100.00) + "元";
        if (!Strings.isNullOrEmpty(memberName)) {
            title = memberName + title;
        }
        dntPaymentPlan.setTitle(GenerateUtil.genPaymentPlanTitle(title));
        dntPaymentPlan.setRefTable("dnt_no_contract_donate");
        dntPaymentPlan.setRefRecordId(dntNoContractDonate.getId());
        dntPaymentPlan.setPaymentDate(null);
        dntPaymentPlan.setAmount(dntNoContractDonate.getAmount());
        dntPaymentPlan.setEnable(1);
        dntPaymentPlan.setPaylogRefTable("pay_wx_unified_order");
        dntPaymentPlan.setStatus(0);
        String orderNo = GenerateUtil.genOrderNo();
        dntPaymentPlan.setOrderNo(orderNo);

        // !!! 以下这些字段没有录入数据
        // payment_date datetime null comment '付款日期【,.!】',
        // received_amount bigint default '0' null comment '已收金额【,.!】',
        // deadline datetime null comment '截止日期【,.!】',
        // remark_ varchar(1000) null comment '备注【,.】',
        // enable_ int null comment '状态',
        // create_time datetime null comment '创建时间',
        // create_by bigint null comment '创建人',
        // update_time datetime null comment '修改时间',
        // update_by bigint null comment '修改人',
        // payment_method_id bigint null comment '付款方式标识【,.!】',
        // paylog_ref_table varchar(50) null comment '付款方式流水记录表【,.%{paylog_weixin:微信支付,paylog_offline:线下支付}】',
        // paylog_ref_record_id bigint null comment '付款方式流水记录标识【,.!】',

        // TODO 增加容错处理
        try{
            paymentPlanService.insert(dntPaymentPlan);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }


        Map<String, String> map = payService.pay(orderNo, dntPaymentPlan.getId().toString(), amount);
        map.put("orderNo", orderNo);
        return map;
    }

//    @Override
//    public Map<String, String> donateContract(DonateContract donateContract, String ip, String memberName, Long paymentPlanId) {
//        Long memberId = donateContract.getMemberId();
//        Integer amount = donateContract.getAmount();
//        DntPaymentPlan dntPaymentPlan = new DntPaymentPlan();
//        String orderNo = GenerateUtil.genOrderNo();
//        String productId = String.valueOf(paymentPlanId);
////        String productId=dntPaymentPlan.getId().toString();
//        Long chu = new Long(100);
//        Long totalFee = amount / chu;
//        Map<String, String> map = payService.pay(orderNo, productId, amount);
//        map.put("orderNo", orderNo);
//        return map;
////        return null;
//    }

    @Override
    public Map<String, String> donateContract(Long memberId, String ip, Long paymentPlanId) {
        //Ip验证

        //生成订单号
        String orderNo = GenerateUtil.genOrderNo();

        //写进支付计划
        paymentPlanMapper.updateOrderNo(orderNo, paymentPlanId);

        //得到支付计划捐赠金额
        PaymentPlanExtension paymentPlanExtension = paymentPlanMapper.selectPaymentPlanByaymentPlanId(paymentPlanId);
        Long totalFee = paymentPlanExtension.getAmount();
//        Long totalFee=Long.parseLong(amount);
        String amount = String.valueOf(totalFee);
        //验证是否已经捐赠
        Long receivedAmount = paymentPlanExtension.getReceivedAmount();
        if (receivedAmount == 0) {
            String productId = String.valueOf(paymentPlanId);
            Map<String, String> map = payService.pay(orderNo, productId, totalFee);
            map.put("orderNo", orderNo);
            map.put("amount", amount);
            return map;
        } else {
            return null;
        }

    }


    /**
     * 支付宝捐赠
     */
    @Override
    public Map<String, String> aliPaydonate(HttpServletRequest httpRequest, HttpServletResponse httpResponse,Donate donate, String ip, String memberName) {

        Integer projectTypeId = donate.getProjectTypeId();
        Integer projectId = donate.getProjectId();
        Long memberId = donate.getMemberId();
        Integer amount = donate.getAmount();

        // 1.写入直接捐赠表 dnt_no_contract_donate
        DntNoContractDonate dntNoContractDonate = new DntNoContractDonate();

        // Integer projectTypeId;          // projectTypeId bigint null comment '项目类型标识【,.$(projectTypeName)@(dntProjectType)】',
        // Integer projectId;             // projectId bigint null comment '项目标识【,.!】',
        // String noContractDonateNo;        // noContractDonateNo varchar(20) null comment '直接捐赠编号【,.!】',
        // Integer memberId;        // memberId bigint null comment '会员标识【,.!】',
        // Integer amount;        // amount bigint default '0' null comment '捐赠金额【,.*】',
        // Integer donatedAmount;        // donatedAmount bigint default '0' null comment '已捐赠金额【,.!】',
        // Integer allocateAmount;        // allocateAmount bigint default '0' null comment '已批资金额【,.!】',
        // String remark;        // remark varchar(1000) null comment '备注【,.】',
        // Integer enable;        // enable int null comment '状态',
        // Date createTime;        // createTime datetime null comment '创建时间',
        // Integer createBy;        // createBy bigint null comment '创建人',
        // Date updateTime;        // updateTime datetime null comment '修改时间',
        // Integer updateBy;        // updateBy bigint null comment '修改人',
        // String ip;        // ip varchar(64) null comment 'ip',
        // Integer appropriationContractId;
        dntNoContractDonate.setProjectTypeId(projectTypeId);
        dntNoContractDonate.setProjectId(projectId);
        dntNoContractDonate.setNoContractDonateNo(GenerateUtil.genNoContractDonateNo());
        dntNoContractDonate.setMemberId(memberId);
        dntNoContractDonate.setAmount(amount);
        dntNoContractDonate.setDonatedAmount(0);
        dntNoContractDonate.setAllocateAmount(0);
        dntNoContractDonate.setEnable(1);
        dntNoContractDonate.setIp(ip);
        dntNoContractDonate.setCreateTime(new Date());

        //根据IP获取具体地址信息
        AliIpDetail aliIpDetail = IPUtil.getIpLocation("ip=" + ip);
        if (aliIpDetail != null) {
            dntNoContractDonate.setIpCountry(aliIpDetail.getCountry());
            dntNoContractDonate.setIpRegion(aliIpDetail.getRegion());
            dntNoContractDonate.setIpArea(aliIpDetail.getArea());
            dntNoContractDonate.setIpCity(aliIpDetail.getCity());
            dntNoContractDonate.setIsp(aliIpDetail.getIsp());
        }
        // TODO 增加容错处理
        noContractDonateService.insert(dntNoContractDonate);

        // 2.写入付款计划表 dnt_payment_plan
        DntPaymentPlan dntPaymentPlan = new DntPaymentPlan();

        String title = "捐赠了" + String.valueOf(amount / 100.00) + "元";
        if (!Strings.isNullOrEmpty(memberName)) {
            title = memberName + title;
        }
        dntPaymentPlan.setTitle(GenerateUtil.genPaymentPlanTitle(title));
        dntPaymentPlan.setRefTable("dnt_no_contract_donate");
        dntPaymentPlan.setRefRecordId(dntNoContractDonate.getId());
        dntPaymentPlan.setPaymentDate(null);
        dntPaymentPlan.setAmount(dntNoContractDonate.getAmount());
        dntPaymentPlan.setEnable(1);
        dntPaymentPlan.setPaylogRefTable("pay_wx_unified_order");
        dntPaymentPlan.setStatus(0);
        String orderNo = GenerateUtil.genOrderNo();
        dntPaymentPlan.setOrderNo(orderNo);

        // !!! 以下这些字段没有录入数据
        // payment_date datetime null comment '付款日期【,.!】',
        // received_amount bigint default '0' null comment '已收金额【,.!】',
        // deadline datetime null comment '截止日期【,.!】',
        // remark_ varchar(1000) null comment '备注【,.】',
        // enable_ int null comment '状态',
        // create_time datetime null comment '创建时间',
        // create_by bigint null comment '创建人',
        // update_time datetime null comment '修改时间',
        // update_by bigint null comment '修改人',
        // payment_method_id bigint null comment '付款方式标识【,.!】',
        // paylog_ref_table varchar(50) null comment '付款方式流水记录表【,.%{paylog_weixin:微信支付,paylog_offline:线下支付}】',
        // paylog_ref_record_id bigint null comment '付款方式流水记录标识【,.!】',

        // TODO 增加容错处理
        paymentPlanService.insert(dntPaymentPlan);

        try {
            Map<String, String> map = aliPayService.pagePay(httpRequest,httpResponse,orderNo, dntPaymentPlan.getId().toString(), amount);
            map.put("orderNo", orderNo);
        }catch (Exception e){
            return null;
        }
        return null;
    }


    // TODO
    /**
     * 支付宝合同捐赠
     */
    @Override
    public Map<String, String> aliPaydonateContract(Long memberId, String ip, Long paymentPlanId) {
        return null;
    }
}

