package com.becheer.donation.service.impl;

import com.becheer.core.support.pay.WxPay;
import com.becheer.core.support.pay.WxPayHelper;
import com.becheer.core.support.pay.WxPayQueryOrderResult;
import com.becheer.core.support.pay.WxPayReturnToWeixin;
import com.becheer.donation.model.extension.wxpay.WxPayPrepayExtension;
import com.becheer.donation.service.IWxPayService;
import com.google.common.base.Strings;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class WxPayServiceImpl implements IWxPayService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WxPayServiceImpl.class);

    @Override
    public Map<String, String> pay(String outTradeNo, String productId, long totalFee) {


        Map<String, String> map = WxPay.unifiedOrder(outTradeNo, productId, String.valueOf(totalFee));

        // TODO: 使用Map还是实体类，各有好处，使用Map，调用该接口的人需要了解微信支付的接口文档，使用实体类直接看实体类属性字段

        return map;

    }

    @Override
    public String payNotify(WxPayQueryOrderResult notifyBody) {

        boolean notifyIsValid = false;
        String failMsg = "";
        if (notifyBody.getResultCode().equals("SUCCESS")) {
            if (notifyBody.getReturnCode().equals("SUCCESS")) {

                // TODO 校验签名验证,并校验返回的订单金额是否与商户侧的订单金额一致

                String sign = "";
                String outTradeNo = "";
                int totalFee = 0;

                if (!notifyBody.getSign().equals(sign)) {
                    failMsg = "签名失败";
                }

                if (notifyBody.getOutTradeNo().equals(outTradeNo)) {
                    failMsg = "无效的订单号";
                }

                if (notifyBody.getTotalFee() == totalFee) {
                    failMsg = "无效的支付金额";
                }
                if (Strings.isNullOrEmpty(failMsg)) {
                    notifyIsValid = true;
                } else {
                    notifyIsValid = false;
                }
            }
        } else {
            // TODO 微信支付接口通知我们支付失败，需要将支付失败的原因显示在对应的业务表中。

        }

        WxPayReturnToWeixin wxPayReturnToWeixin = new WxPayReturnToWeixin();
        if (notifyBody.getReturnCode().equals("SUCCESS") && notifyBody.getResultCode().equals("SUCCESS") && notifyIsValid) {
            wxPayReturnToWeixin.setReturnCode("SUCCESS");
            wxPayReturnToWeixin.setReturnMsg("OK");
        } else {
            wxPayReturnToWeixin.setReturnCode("FAIL");
            wxPayReturnToWeixin.setReturnMsg("签名失败");
        }
        return wxPayReturnToWeixin.toXML();
    }
}
