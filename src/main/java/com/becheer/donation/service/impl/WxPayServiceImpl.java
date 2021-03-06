package com.becheer.donation.service.impl;

import com.becheer.core.support.pay.WxPay;
import com.becheer.core.support.pay.WxPayHelper;
import com.becheer.core.util.XmlUtil;
import com.becheer.donation.model.*;
import com.becheer.donation.model.base.ResponseDto;
import com.becheer.donation.model.extension.contract.MemberContractDetailExtension;
import com.becheer.donation.model.extension.contract.NoContractDonateExtension;
import com.becheer.donation.model.extension.member.MemberSessionExtension;
import com.becheer.donation.model.extension.project.ProjectDetailExtension;
import com.becheer.donation.service.*;
import com.becheer.donation.strings.Message;
import com.becheer.donation.utils.DateUtils;
import com.becheer.donation.utils.RedisUtil;
import com.becheer.donation.utils.StringUtil;
import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;

@Service
public class WxPayServiceImpl implements IWxPayService {

    private static final Logger logger = LoggerFactory.getLogger(WxPayServiceImpl.class);

    @Resource
    private IPayWxUnifiedOrderService payWxUnifiedOrderService;

    @Resource
    private IDntPaymentPlanService paymentPlanService;

    @Resource
    private IDntNoContractDonateService dntnoContractDonateService;

    @Resource
    private INoContractDonateService noContractDonateService;

    @Resource
    private IProjectProgressService projectProgressService;

    @Resource
    private IDntContractProjectService dntContractProjectService;

    @Resource
    private IProjectService projectService;

    @Resource
    private IContractService contractService;

    @Resource
    private IContractProjectAcceptorSerivce contractProjectAcceptorSerivce;

    @Resource
    private IProgressService progressService;


    @Override
    public Map<String, String> pay(String outTradeNo, String productId, long totalFee) {

        Map<String, String> params = WxPayHelper.buildUnifiedOrderParasMap(outTradeNo, productId, String.valueOf(totalFee));//String.valueOf(totalFee)

        String prepayXML = WxPay.unifiedOrder(params);
        Map prepayMap = XmlUtil.parseXml2Map(prepayXML);
        if (WxPayHelper.verifyNotify(prepayMap)) {
            Iterator keyIterator = prepayMap.keySet().iterator();
            while (keyIterator.hasNext()) {
                String key = (String) keyIterator.next();
                if (params.containsKey(key)) {
                    continue;
                }
                String value = (String) prepayMap.get(key);
                params.put(key, value);
            }
            payWxUnifiedOrderService.insert(params);
        }

        return prepayMap;
    }

    @Override
    public String payNotify(String notifyXML) {
        logger.info("收到微信支付结果通知:");
        logger.info(notifyXML);

        // 返回给微信支付平台的结果
        Map<String, String> returnToWxPay = new HashMap<>();

        // 微信支付结果XML
        Map<String, String> notify = WxPayHelper.xmlToMap(notifyXML);

        String returnCode = notify.get("return_code");
        String returnMsg = notify.get("return_msg");

        if ("FAIL".equals(returnCode)) {
            returnToWxPay.put("return_code", "SUCCESS");
            returnToWxPay.put("return_msg", "OK");
            return WxPayHelper.toXml(returnToWxPay);
        }


//         验证签名
//        if (!WxPayHelper.verifyNotify(notify)) {
//            logger.warn("微信支付结果通知: !!!签名失败!!!");
//            returnToWxPay.put("return_code", "FAIL");
//            returnToWxPay.put("return_msg", "签名失败");
//            return WxPayHelper.toXml(returnToWxPay);
//        }

        // 验证appid、mch_id等
        if (!WxPayHelper.verifyAppIdAndMchId(notify)) {
            logger.warn("微信支付结果通知: !!!app_id或mch_id错误，确认是否修改了微信支付的配置文件!!!");
            returnToWxPay.put("return_code", "SUCCESS");
            returnToWxPay.put("return_msg", "OK");
            return WxPayHelper.toXml(returnToWxPay);
        }

        // 微信支付平台的提醒: 推荐的做法是,
        // 1. 当收到通知进行处理时，首先检查对应业务数据的状态，判断该通知是否已经处理过，
        // 2. 如果没有处理过再进行处理
        // 3. 如果处理过直接返回结果成功。
        // 4. 在对业务数据进行状态检查和处理之前，要采用数据锁进行并发控制，以避免函数重入造成的数据混乱。

        String outTradeNo = "";
        if (notify.get("out_trade_no") != null) {
            outTradeNo = notify.get("out_trade_no");
        } else {
            logger.warn("微信支付结果通知: !!!获取统一下单数据时出现异常【out_trade_no=" + outTradeNo + "】:");
            returnToWxPay.put("return_code", "FAIL");
            returnToWxPay.put("return_msg", "缺少参数:商户订单号out_trade_no");
            return WxPayHelper.toXml(returnToWxPay);
        }

        // 按照out_trade_no获取微信支付统一下单接口的数据

        PayWxUnifiedOrder unifiedOrder = null;

        try {
            unifiedOrder = payWxUnifiedOrderService.getPayWxUnifiedOrderByOutTradeNo(outTradeNo);
        } catch (Exception e) {
            logger.warn("微信支付结果通知: !!!获取统一下单数据时出现异常: " + e.getMessage());
            returnToWxPay.put("return_code", "SUCCESS");
            returnToWxPay.put("return_msg", "OK");
            return WxPayHelper.toXml(returnToWxPay);
        }
        if (unifiedOrder == null) {
            logger.warn("微信支付结果通知: !!!商户订单号对应的业务数据不存在【out_trade_no=" + outTradeNo + "】");
            returnToWxPay.put("return_code", "FAIL");
            returnToWxPay.put("return_msg", "商户订单号out_trade_no=" + outTradeNo + "对应的业务数据不存在");
            return WxPayHelper.toXml(returnToWxPay);
        }

        // 检查业务数据状态
        // 1. 订单已经被处理过。重复通知。
        // 觉得这种可能性不大，如果有出现需要确认我们的业务逻辑是否有问题或被人修改过数据。写warn日志
        if ("SUCCESS".equals(unifiedOrder.getNotifyReturnCode())) {
            logger.warn("微信支付结果通知: !!!通知相关的业务数据状态被标识为已完成，但仍收到微信支付结果通知【out_trade_no=" + outTradeNo + "】!!!");
            returnToWxPay.put("return_code", "SUCCESS");
            return WxPayHelper.toXml(returnToWxPay);
        }

        // https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=9_7
        // 特别提醒：商户系统对于支付结果通知的内容一定要做签名验证,并校验返回的订单金额是否与商户侧的订单金额一致，防止数据泄漏导致出现“假通知”，造成资金损失。
        Integer totalFee = -1;
        if (notify.get("total_fee") != null) {
            totalFee = Integer.valueOf(notify.get("total_fee"));
            if (!totalFee.equals(unifiedOrder.getTotalFee())) {
                logger.warn("微信支付结果通知: !!!通知相关的业务数据的订单金额和商侧的订单金额不一致【out_trade_no=" + outTradeNo + "】");
                returnToWxPay.put("return_code", "FAIL");
                returnToWxPay.put("return_msg", "返回的订单金额是否与商户侧的订单金额不一致");
                return WxPayHelper.toXml(returnToWxPay);
            }
        }

        // 更新业务数据状态
        Date paymentDate = null;
        String timeEnd = notify.get("time_end");
        String time = null;
        if (!Strings.isNullOrEmpty(timeEnd)) {
            time = DateUtils.strToDateFormat(timeEnd);
            paymentDate = DateUtils.StrToDate(time);
        }


        //查询poject
        DntPaymentPlan dntPaymentPlan = new DntPaymentPlan();
        dntPaymentPlan = paymentPlanService.selectPaymentPlanByOrderNo(outTradeNo);
        String refTable = dntPaymentPlan.getRefTable();
        Integer refRecordId = dntPaymentPlan.getRefRecordId();

        //支付成功的通知重复发送
        if (dntPaymentPlan.getStatus() == 1) {
            logger.warn("微信支付结果通知: !!!重复通知【out_trade_no=" + outTradeNo + "】");
            returnToWxPay.put("return_code", "FAIL");
            returnToWxPay.put("return_msg", "重复通知");
            return WxPayHelper.toXml(returnToWxPay);
        }

        //回写付款表及分摊金额
        paymentPlanService.payment(outTradeNo, totalFee, paymentDate, "pay_wx_unified_order", unifiedOrder.getId(), 1);

        Float free = Float.valueOf(totalFee);

        if (refTable.equals("dnt_no_contract_donate")) {
            Map map = dntnoContractDonateService.selectNoContractDonateById(refRecordId);
            if (map != null) {
                long memberId = map.containsKey("member_id") ? (long) map.get("member_id") : 0;
                String memberName = map.containsKey("member_name") ? map.get("member_name").toString() : "";
                memberName = StringUtil.getEncryptedMemberName(memberName);
                long projectId = map.containsKey("project_id") ? (long) map.get("project_id") : 0;
                String projectName = map.containsKey("project_name") ? map.get("project_name").toString() : "";

                //写入progress
                String title = "捐赠成功";
                String content = memberName + "对" + projectName + "项目捐赠了" + free / 100.00 + "元";
                if (projectId != 0) {
                    //项目进展
                    projectProgressService.insert(projectId, title, content, content, 5);
                }
                if (memberId != 0) {
                    //直接捐赠会员时间轴
                    content = projectName.equals("") ? "捐赠了" + free / 100.00 + "元" : content;
                    progressService.AddProgress(title, content, "dnt_member", memberId, memberId, 1, 3);
                }

//            List<NoContractDonateExtension> resultList = noContractDonateService.GetRecentNoContractDonate(projectId, 1);
//            NoContractDonateExtension noContractDonateExtension = resultList.get(0);
//            String location = noContractDonateExtension.getLocation();
            }

        } else {
            //写进进程表
//            Integer i = 0; //注释没有使用的代码 -- by liaojianhong
            List<DntContractProject> projects = dntContractProjectService.selectProjectIdBycontraId(refRecordId);
            MemberContractDetailExtension memberContractDetailExtension = contractService.GetContractContent(refRecordId);
            Long memberId = memberContractDetailExtension.getMemberId();
            RedisUtil.delContractkey(refRecordId);

            List<Long> contractProjectIds = new ArrayList<>();

            //合同期数
            //            Integer contractTime = contractService.selectTimeById(refRecordId); //注释没有使用的代码 -- by liaojianhong
            //合同缺的金额

            for (DntContractProject project : projects) {
//                projectId = project.getProjectId(); //注释没有使用的代码 -- by liaojianhong
                Long contractProjectId = project.getId();
                //清除缓存
                contractProjectIds.add(contractProjectId);
                RedisUtil.delContractProjectkey(contractProjectId);
            }

            List<Long> ids = contractProjectAcceptorSerivce.selectByContractProjectIds(contractProjectIds);
            for (Long id : ids) {
                RedisUtil.delContractProjectAcceptorkey(id);
            }

            //合同捐赠会员时间轴
            String content = memberContractDetailExtension.getContractNo() + "合同捐赠了" + free / 100.00 + "元";
            progressService.AddProgress("捐赠成功", content, "dnt_member", memberId, memberId, 1, 3);

        }

        Long id_ = paymentPlanService.selectIdByOrderNo(outTradeNo);
        RedisUtil.delPaymentPlankey(id_);


        // 签名、商户信息、业务数据状态、订单金额都没问题的情况下

        String resultCode = notify.get("result_code");
        String errCode = notify.get("err_code");
        String errCodeMsg = notify.get("err_code_msg");
        payWxUnifiedOrderService.updateNotifyXML(outTradeNo, returnCode, returnMsg, resultCode, errCode, errCodeMsg, notifyXML);

        returnToWxPay.put("return_code", "SUCCESS");
        returnToWxPay.put("return_msg", "OK");
        return WxPayHelper.toXml(returnToWxPay);

        //测试
//        Date now=new Date();
//        paymentPlanService.updateDonate("DNT_9643478767", 150, now);
//        return null;
    }

    @Override
    public Map status(String orderNo) {
        return payWxUnifiedOrderService.status(orderNo);
    }

    // 测试数据
    /**
     * FAIL 签名错误
     * curl -X POST -H "Content-type:text/xml" -d '<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[签名错误]]></return_msg></xml>' http://localhost:8080/wxpay/notify
     *
     * SUCCESS
     <xml>
     <appid><![CDATA[wx2421b1c4370ec43b]]></appid>
     <attach><![CDATA[支付测试]]></attach>
     <bank_type><![CDATA[CFT]]></bank_type>
     <fee_type><![CDATA[CNY]]></fee_type>
     <is_subscribe><![CDATA[Y]]></is_subscribe>
     <mch_id><![CDATA[10000100]]></mch_id>
     <nonce_str><![CDATA[5d2b6c2a8db53831f7eda20af46e531c]]></nonce_str>
     <openid><![CDATA[oUpF8uMEb4qRXf22hE3X68TekukE]]></openid>
     <out_trade_no><![CDATA[1409811653]]></out_trade_no>
     <result_code><![CDATA[SUCCESS]]></result_code>
     <return_code><![CDATA[SUCCESS]]></return_code>
     <sign><![CDATA[B552ED6B279343CB493C5DD0D78AB241]]></sign>
     <sub_mch_id><![CDATA[10000100]]></sub_mch_id>
     <time_end><![CDATA[20140903131540]]></time_end>
     <total_fee>1</total_fee>
     <trade_type><![CDATA[JSAPI]]></trade_type>
     <transaction_id><![CDATA[1004400740201409030005092168]]></transaction_id>
     </xml>

     * curl -X POST -H "Content-type:text/xml" -d '<xml> <appid><![CDATA[wx2421b1c4370ec43b]]></appid> <attach><![CDATA[支付测试]]></attach> <bank_type><![CDATA[CFT]]></bank_type> <fee_type><![CDATA[CNY]]></fee_type> <is_subscribe><![CDATA[Y]]></is_subscribe> <mch_id><![CDATA[10000100]]></mch_id> <nonce_str><![CDATA[5d2b6c2a8db53831f7eda20af46e531c]]></nonce_str> <openid><![CDATA[oUpF8uMEb4qRXf22hE3X68TekukE]]></openid> <out_trade_no><![CDATA[5837974697544152]]></out_trade_no> <result_code><![CDATA[SUCCESS]]></result_code> <return_code><![CDATA[SUCCESS]]></return_code> <sign><![CDATA[B552ED6B279343CB493C5DD0D78AB241]]></sign> <sub_mch_id><![CDATA[10000100]]></sub_mch_id> <time_end><![CDATA[20140903131540]]></time_end> <total_fee>100</total_fee> <trade_type><![CDATA[JSAPI]]></trade_type> <transaction_id><![CDATA[1004400740201409030005092168]]></transaction_id> </xml>' http://localhost:8080/wxpay/notify
     */
}
