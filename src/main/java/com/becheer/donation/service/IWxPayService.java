package com.becheer.donation.service;

import java.util.Map;

public interface IWxPayService {

    /**
     * 微信支付
     */
    Map<String, String> pay(String outTradeNo, String productId, long totalFee);

    String payNotify(String notifyXML);

    Map status(String orderNo);
}
