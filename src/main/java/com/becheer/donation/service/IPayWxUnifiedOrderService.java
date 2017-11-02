package com.becheer.donation.service;

import com.becheer.donation.model.PayWxUnifiedOrder;

import java.util.Map;

public interface IPayWxUnifiedOrderService {

    int insert(Map<String, String> map);

    int insert(PayWxUnifiedOrder model);

    int update(Map<String, String> map);

    int update(PayWxUnifiedOrder model);

    int updateNotifyXML(String outTradeNo, String returnCode, String returnMsg, String resultCode, String errCode, String errCodeMsg, String notifyXML);

    PayWxUnifiedOrder getPayWxUnifiedOrderByOutTradeNo(String outTradeNo);

    Map status(String orderNo);
}

