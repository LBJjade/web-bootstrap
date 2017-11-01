package com.becheer.donation.service;

import com.becheer.core.support.pay.WxPayQueryOrderResult;
import com.becheer.core.support.pay.WxPayReturnToWeixin;
import com.becheer.donation.model.extension.wxpay.WxPayPrepayExtension;

import java.util.Map;

public interface IWxPayService {

    /**
     * 微信支付
     */
    Map<String, String> pay(String outTradeNo, String productId, long totalFee);

    String payNotify(String notifyXML);
}
