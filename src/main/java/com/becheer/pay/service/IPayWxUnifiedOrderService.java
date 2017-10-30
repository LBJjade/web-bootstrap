package com.becheer.pay.service;

import com.becheer.pay.model.PayWxUnifiedOrder;

public interface IPayWxUnifiedOrderService {

    int insert(PayWxUnifiedOrder model);

    int update(PayWxUnifiedOrder model);

    PayWxUnifiedOrder getPayWxUnifiedOrderByOutTradeNo(String outTradeNo);
}

