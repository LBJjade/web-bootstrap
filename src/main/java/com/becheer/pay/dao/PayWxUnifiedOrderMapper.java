package com.becheer.pay.dao;

/*
* PayWxUnifiedOrderMapper
* Date :
*/

import com.becheer.pay.model.PayWxUnifiedOrder;
import org.springframework.stereotype.Component;

@Component
public interface PayWxUnifiedOrderMapper {

    int insert(PayWxUnifiedOrder model);

    int update(PayWxUnifiedOrder model);

    PayWxUnifiedOrder getPayWxUnifiedOrderByOutTradeNo(String outTradeNo);
}
