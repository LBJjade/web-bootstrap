package com.becheer.donation.dao;

/*
* PayWxUnifiedOrderMapper
* Date :
*/

import com.becheer.donation.model.PayWxUnifiedOrder;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public interface PayWxUnifiedOrderMapper {

    int insert(Map<String, String> map);

    int insert(PayWxUnifiedOrder model);

    int update(Map<String, String> map);

    int update(PayWxUnifiedOrder model);

    int updateNotify(String outTradeNo, String notifyXML);

    int updateNotifyXML(String outTradeNo, String returnCode, String returnMsg, String resultCode, String errCode, String errCodeMsg, String notifyXML);

    PayWxUnifiedOrder getPayWxUnifiedOrderByOutTradeNo(String outTradeNo);

    Map status(String outTradeNo);
}
