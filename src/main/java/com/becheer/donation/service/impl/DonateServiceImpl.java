package com.becheer.donation.service.impl;

import com.becheer.donation.model.DntNoContractDonate;
import com.becheer.donation.model.extension.donate.Donate;
import com.becheer.donation.model.extension.wxpay.WxPayPrepayExtension;
import com.becheer.donation.service.IDntNoContractDonateService;
import com.becheer.donation.service.IDonateService;
import com.becheer.donation.utils.GenerateUtil;
import com.becheer.donation.utils.IPUtil;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class DonateServiceImpl implements IDonateService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DonateServiceImpl.class);

    @Resource
    private IDntNoContractDonateService service;


    public WxPayPrepayExtension donate(Donate donate) {
        return donate(donate, null);
    }

    public WxPayPrepayExtension donate(Donate donate, String ip) {
        Integer projectTypeId = donate.getProjectTypeId();
        Integer projectId = donate.getProjectId();
        long memberId = donate.getMemberId();
        Integer amount = donate.getAmount();

        // TODO 1.写入直接捐赠表 dnt_no_contract_donate
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

        service.insert(dntNoContractDonate);

        // TODO 2.写入付款计划表 dnt_payment_plan



        return null;
    }
}

