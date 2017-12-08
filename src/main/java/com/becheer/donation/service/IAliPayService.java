package com.becheer.donation.service;

import com.becheer.donation.model.base.ResponseDto;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public interface IAliPayService {

    /**
     * 支付宝支付
     */
    //电脑支付
    Map<String, String> pagePay(HttpServletRequest httpRequest, HttpServletResponse httpResponse,String outTradeNo, String productId, long totalFee)throws ServletException, IOException;

    //get return参数处理
    ResponseDto handelReturn(String returnXML);

    //验签
    ResponseDto signVerfied(HttpServletRequest httpRequest,String notifyXml);

    //查询
    Map<String, String> tradeQuery(String outTradeNo,String tradeNo);

    //退款
    Map<String, String> tradeRefund();

    //退款查询
    Map<String, String> tradeRefundQuery();

    //交易关闭接口
    Map<String, String> tradeClose();

    //查询对账单下载接口
    Map<String, String> tradeDownLoadQuery();

//    Map status(String orderNo);

    /**
     * 当面付
     */
//        Map<String, String> pay(String outTradeNo, String productId, long totalFee) throws AlipayApiException;

//       String payNotify(String notifyXML);

}
