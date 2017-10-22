package com.becheer.donation.service;

import com.becheer.core.support.pay.WxPayQueryOrderResult;
import com.becheer.core.support.pay.WxPayReturnToWeixin;
import com.becheer.donation.model.extension.wxpay.WxPayPrepayExtension;

public interface IWxPayService {

    /**
     * 微信支付
     */
    WxPayPrepayExtension pay(String outTradeNo, String productId, long totalFee);

    String payNotify(WxPayQueryOrderResult notifyBody);
}
