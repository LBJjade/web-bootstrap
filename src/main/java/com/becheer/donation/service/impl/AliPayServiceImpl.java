package com.becheer.donation.service.impl;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradePrecreateRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.becheer.donation.service.IAliPayService;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Service
public class AliPayServiceImpl implements IAliPayService {

    public AliPayServiceImpl() {
        super();
    }

    //电脑网站支付测试
    @Override
    public Map<String, String> pagePay(HttpServletRequest httpRequest, HttpServletResponse httpResponse, String outTradeNo, String productId, long totalFee) throws ServletException, IOException {
        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", "2017120400377299", "APP_PRIVATE_KEY", "json", "GBK", "ALIPAY_PUBLIC_KEY", "RSA2"); //获得初始化的AlipayClient
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();//创建API对应的request
        alipayRequest.setReturnUrl("http://domain.com/CallBack/return_url.jsp");
        alipayRequest.setNotifyUrl("http://domain.com/CallBack/notify_url.jsp");//在公共参数中设置回跳和通知地址
        String param="{" +
                "    \"out_trade_no\":\"20150320010101001\"," +
                "    \"product_code\":\"FAST_INSTANT_TRADE_PAY\"," +
                "    \"total_amount\":88.88," +
                "    \"subject\":\"Iphone6 16G\"," +
                "    \"body\":\"Iphone6 16G\"," +
                "    \"passback_params\":\"merchantBizType%3d3C%26merchantBizNo%3d2016010101111\"," +
                "    \"extend_params\":{" +
                "    \"sys_service_provider_id\":\"2088511833207846\"" +
                "    }" +
                "  }";
        alipayRequest.setBizContent("{" +
                "    \"out_trade_no\":\"20150320010101001\"," +
                "    \"product_code\":\"FAST_INSTANT_TRADE_PAY\"," +
                "    \"total_amount\":88.88," +
                "    \"subject\":\"Iphone6 16G\"," +
                "    \"body\":\"Iphone6 16G\"," +
                "    \"passback_params\":\"merchantBizType%3d3C%26merchantBizNo%3d2016010101111\"," +
                "    \"extend_params\":{" +
                "    \"sys_service_provider_id\":\"2088511833207846\"" +
                "    }" +
                "  }");//填充业务参数
        String form = "";
        try {
            form = alipayClient.pageExecute(alipayRequest).getBody(); //调用SDK生成表单
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        httpResponse.setContentType("text/html;charset=" + "GBK");
        httpResponse.getWriter().write(form);//直接将完整的表单html输出到页面
        httpResponse.getWriter().flush();
        httpResponse.getWriter().close();
        return null;
    }


    //测试
    //alipay.trade.query(统一收单线下交易查询)
    @Override
    public Map<String, String> tradeQuery(String outTradeNo, String tradeNo) {
        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do","2017120400377299","your private_key","json","GBK","alipay_public_key","RSA2");
        AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
        request.setBizContent("{" +
                "\"out_trade_no\":\"20150320010101001\"," +
                "\"trade_no\":\"2014112611001004680073956707\"" +
                "}");
        AlipayTradeQueryResponse response = null;
        try {
            response = alipayClient.execute(request);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        if(response.isSuccess()){
            System.out.println("调用成功");
        } else {
            System.out.println("调用失败");
        }
        return null;
    }

    //alipay.trade.refund(统一收单交易退款接口)
    @Override
    public Map<String, String> tradeRefund() {
        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do","2017120400377299","your private_key","json","GBK","alipay_public_key","RSA2");
        AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
        String biz="";

        request.setBizContent("{" +
                "\"out_trade_no\":\"20150320010101001\"," +
                "\"trade_no\":\"2014112611001004680073956707\"," +
                "\"refund_amount\":200.12," +
                "\"refund_reason\":\"正常退款\"," +
                "\"out_request_no\":\"HZ01RF001\"," +
                "\"operator_id\":\"OP001\"," +
                "\"store_id\":\"NJ_S_001\"," +
                "\"terminal_id\":\"NJ_T_001\"" +
                "  }");
        AlipayTradeRefundResponse response = null;
        try {
            response = alipayClient.execute(request);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        if(response.isSuccess()){
            System.out.println("调用成功");
        } else {
            System.out.println("调用失败");
        }
        return null;
    }

    /**
     * 当面付
     * @param orderNo
     * @return
     */

    //    //测试当面付支付接口
//    //alipay.trade.precreate(统一收单线下交易预创建)
//    @Override
//    public Map<String, String> pay(String outTradeNo, String productId, long totalFee) throws AlipayApiException {
//        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", "2017120400377299", "your private_key", "json", "GBK", "alipay_public_key", "RSA2");
//        AlipayTradePrecreateRequest request = new AlipayTradePrecreateRequest();
//        request.setBizContent("{" +
//                "\"out_trade_no\":\"20150320010101001\"," +
//                "\"seller_id\":\"2088102146225135\"," +
//                "\"total_amount\":88.88," +
//                "\"discountable_amount\":8.88," +
//                "\"subject\":\"Iphone616G\"," +
//                "\"goods_detail\":[{" +
//                "\"goods_id\":\"apple-01\"," +
//                "\"goods_name\":\"ipad\"," +
//                "\"quantity\":1," +
//                "\"price\":2000," +
//                "\"goods_category\":\"34543238\"," +
//                "\"body\":\"特价手机\"," +
//                "\"show_url\":\"http://www.alipay.com/xxx.jpg\"" +
//                "}]," +
//                "\"body\":\"Iphone616G\"," +
//                "\"operator_id\":\"yx_001\"," +
//                "\"store_id\":\"NJ_001\"," +
//                "\"disable_pay_channels\":\"pcredit,moneyFund,debitCardExpress\"," +
//                "\"enable_pay_channels\":\"pcredit,moneyFund,debitCardExpress\"," +
//                "\"terminal_id\":\"NJ_T_001\"," +
//                "\"extend_params\":{" +
//                "\"sys_service_provider_id\":\"2088511833207846\"" +
//                "}," +
//                "\"timeout_express\":\"90m\"," +
//                "\"business_params\":\"{\\\"data\\\":\\\"123\\\"}\"" +
//                "}");
//        AlipayTradePrecreateResponse response = alipayClient.execute(request);
//        System.out.print(response.getBody());
//
//        if (response.isSuccess()) {
//            System.out.println("调用成功");
//        } else {
//            System.out.println("调用失败");
//        }
//        return null;
//    }


    //测试下单通知接口
//    //
//    @Override
//    public String payNotify(String notifyXML) {
//        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", "app_id", "your private_key", "json", "GBK", "alipay_public_key", "RSA2");
//        AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
//        request.setBizContent("{" +
//                "\"out_trade_no\":\"20150320010101001\"," +
//                "\"trade_no\":\"2014112611001004680073956707\"" +
//                "}");
//        try {
//            AlipayTradeQueryResponse response = alipayClient.execute(request);
//            if (response.isSuccess()) {
//                System.out.println("调用成功");
//            } else {
//                System.out.println("调用失败");
//            }
//        } catch (Exception e) {
//            return null;
//        }
//        return null;
//    }

    @Override
    public Map status(String orderNo) {
        return null;
    }


}
