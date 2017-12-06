package com.becheer.donation.service;

import com.alipay.api.AlipayApiException;
import org.springframework.stereotype.Service;

import java.util.Map;

public interface IAliPayService {

    /**
     * 支付宝支付
     */
    Map<String, String> pay(String outTradeNo, String productId, long totalFee) throws AlipayApiException;

    String payNotify(String notifyXML);

    Map status(String orderNo);

}
