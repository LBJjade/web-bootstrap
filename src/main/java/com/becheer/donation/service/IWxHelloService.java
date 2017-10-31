package com.becheer.donation.service;

import com.becheer.core.support.pay.WxPayQueryOrderResult;

import java.util.Map;

public interface IWxHelloService {

    /**
     * 微信支付
     */
    Map<String, String> pay(String outTradeNo, String productId, long totalFee);

    String payNotify(WxPayQueryOrderResult notifyBody);
}
