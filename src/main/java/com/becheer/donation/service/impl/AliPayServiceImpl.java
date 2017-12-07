package com.becheer.donation.service.impl;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradePayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.*;
import com.alipay.api.response.*;
import com.becheer.core.support.alipay.AliPay;
import com.becheer.donation.configs.AliPayConfig;
import com.becheer.donation.controller.WxPayController;
import com.becheer.donation.model.base.ResponseDto;
import com.becheer.donation.service.IAliPayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Service
public class AliPayServiceImpl implements IAliPayService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AliPayServiceImpl.class);

    public AliPayServiceImpl() {
        super();
    }

    public @Autowired AliPayConfig aliPayConfig;

    //电脑网站支付测试
    @Override
    public Map<String, String> pagePay(HttpServletRequest httpRequest, HttpServletResponse httpResponse, String outTradeNo, String productId, long totalFee) throws ServletException, IOException {

        //用AliPay工具
        AlipayTradePayModel alipayTradePayModel = new AlipayTradePayModel();
        alipayTradePayModel.setOutTradeNo("20150320010101001");
        alipayTradePayModel.setProductCode("FAST_INSTANT_TRADE_PAY");
        alipayTradePayModel.setTotalAmount("88.88");
        alipayTradePayModel.setSubject("Iphone6 16G");
        alipayTradePayModel.setBody("Iphone6 16G");
        try {
            AliPay.tradePage(httpResponse, alipayTradePayModel, "http://donation.becheer.com/aliPay/aliNotify", "http://donation.becheer.com/aliPay/aliReturn");
            AliPay.tradePage(httpResponse, alipayTradePayModel, "http://donation.becheer.com/aliPay/aliNotify", "http://donation.becheer.com/aliPay/aliReturn");
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }

        //TODO
        //处理配置类
        AlipayClient alipayClientg = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", "2017120400377299", "APP_PRIVATE_KEY", "json", "GBK", "ALIPAY_PUBLIC_KEY", "RSA2"); //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(aliPayConfig.getServiceUrl(),aliPayConfig.getAppId(), aliPayConfig.getPrivateKey(), aliPayConfig.getFormat(),aliPayConfig.getCharset(),aliPayConfig.getAlipayPublicKey(), aliPayConfig.getSignType()); //获得初始化的AlipayClient


        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();//创建API对应的request
        alipayRequest.setReturnUrl("http://donation.becheer.com/aliPay/aliReturn");
        alipayRequest.setNotifyUrl("http://donation.becheer.com/aliPay/aliNotify");//在公共参数中设置回跳和通知地址


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

        //TODO
        // 处理返回参数

        return null;
    }

    //异步通知验签
    @Override
    public ResponseDto signVerfied(HttpServletRequest httpRequest,String notifyXml) {
        Map<String, String> map = AliPay.toMap(httpRequest);
        try {
            boolean signVerifiedg = AlipaySignature.rsaCheckV1(map, "ALIPAY_PUBLIC_KEY", "GBK", "RSA2"); //调用SDK验证签名
            boolean signVerified = AlipaySignature.rsaCheckV1(map, aliPayConfig.getAlipayPublicKey(), aliPayConfig.getCharset(), aliPayConfig.getSignType()); //调用SDK验证签名
            if(signVerified){
                // TODO 验签成功后，按照支付结果异步通知中的描述，对支付结果中的业务内容进行二次校验，校验成功后在response中返回success并继续商户自身业务处理，校验失败返回failure

            }else{
                // TODO 验签失败则记录异常日志，并在response中返回failure.
                LOGGER.error("signVerfied", "");
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return null;
    }

    //测试
    //alipay.trade.query(统一收单线下交易查询)
    @Override
    public Map<String, String> tradeQuery(String outTradeNo, String tradeNo) {

        AlipayClient alipayClientg = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", "2017120400377299", "your private_key", "json", "GBK", "alipay_public_key", "RSA2");
        AlipayClient alipayClient = new DefaultAlipayClient(aliPayConfig.getServiceUrl(),aliPayConfig.getAppId(), aliPayConfig.getPrivateKey(), aliPayConfig.getFormat(),aliPayConfig.getCharset(),aliPayConfig.getAlipayPublicKey(), aliPayConfig.getSignType()); //获得初始化的AlipayClient
        AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
        request.setBizContent("{" +
                "\"out_trade_no\":\"20150320010101001\"," +
                "\"trade_no\":\"2014112611001004680073956707\"" +
                "}");
        AlipayTradeQueryResponse response = null;
        try {
            response = alipayClient.execute(request);
            //TODO
            // 处理返回参数（response）

        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        if (response.isSuccess()) {
            System.out.println("调用成功");
        } else {
            System.out.println("调用失败");
        }


        return null;
    }

    //alipay.trade.refund(统一收单交易退款接口)
    @Override
    public Map<String, String> tradeRefund() {
        AlipayClient alipayClientg = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", "2017120400377299", "your private_key", "json", "GBK", "alipay_public_key", "RSA2");
        AlipayClient alipayClient = new DefaultAlipayClient(aliPayConfig.getServiceUrl(),aliPayConfig.getAppId(), aliPayConfig.getPrivateKey(), aliPayConfig.getFormat(),aliPayConfig.getCharset(),aliPayConfig.getAlipayPublicKey(), aliPayConfig.getSignType()); //获得初始化的AlipayClient
        AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
        String biz = "";
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
        if (response.isSuccess()) {
            System.out.println("调用成功");
        } else {
            System.out.println("调用失败");
        }
        return null;
    }


    /**
     * alipay.trade.fastpay.refund.query(统一收单交易退款查询)
     */
    @Override
    public Map<String, String> tradeRefundQuery() {
        //
        AlipayClient alipayClientg = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", "app_id", "your private_key", "json", "GBK", "alipay_public_key", "RSA2");
        AlipayClient alipayClient = new DefaultAlipayClient(aliPayConfig.getServiceUrl(),aliPayConfig.getAppId(), aliPayConfig.getPrivateKey(), aliPayConfig.getFormat(),aliPayConfig.getCharset(),aliPayConfig.getAlipayPublicKey(), aliPayConfig.getSignType()); //获得初始化的AlipayClient
        AlipayTradeFastpayRefundQueryRequest request = new AlipayTradeFastpayRefundQueryRequest();
        request.setBizContent("{" +
                "\"trade_no\":\"20150320010101001\"," +
                "\"out_trade_no\":\"2014112611001004680073956707\"," +
                "\"out_request_no\":\"2014112611001004680073956707\"" +
                "}");
        AlipayTradeFastpayRefundQueryResponse response = null;
        try {
            response = alipayClient.execute(request);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        if (response.isSuccess()) {
            System.out.println("调用成功");
        } else {
            System.out.println("调用失败");
        }
        return null;
    }

    /**
     * alipay.trade.close(统一收单交易关闭接口)
     */
    @Override
    public Map<String, String> tradeClose() {
        AlipayClient alipayClientg = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", "app_id", "your private_key", "json", "GBK", "alipay_public_key", "RSA2");
        AlipayClient alipayClient = new DefaultAlipayClient(aliPayConfig.getServiceUrl(),aliPayConfig.getAppId(), aliPayConfig.getPrivateKey(), aliPayConfig.getFormat(),aliPayConfig.getCharset(),aliPayConfig.getAlipayPublicKey(), aliPayConfig.getSignType()); //获得初始化的AlipayClient
        AlipayTradeCloseRequest request = new AlipayTradeCloseRequest();
        request.setBizContent("{" +
                "\"trade_no\":\"2013112611001004680073956707\"," +
                "\"out_trade_no\":\"HZ0120131127001\"," +
                "\"operator_id\":\"YX01\"" +
                "  }");
        AlipayTradeCloseResponse response = null;
        try {
            response = alipayClient.execute(request);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        if (response.isSuccess()) {
            System.out.println("调用成功");
        } else {
            System.out.println("调用失败");
        }
        return null;
    }

    /**
     * alipay.data.dataservice.bill.downloadurl.query(查询对账单下载地址)
     */
    @Override
    public Map<String, String> tradeDownLoadQuery() {
        AlipayClient alipayClientg = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", "app_id", "your private_key", "json", "GBK", "alipay_public_key", "RSA2");
        AlipayClient alipayClient = new DefaultAlipayClient(aliPayConfig.getServiceUrl(),aliPayConfig.getAppId(), aliPayConfig.getPrivateKey(), aliPayConfig.getFormat(),aliPayConfig.getCharset(),aliPayConfig.getAlipayPublicKey(), aliPayConfig.getSignType()); //获得初始化的AlipayClient
        AlipayDataDataserviceBillDownloadurlQueryRequest request = new AlipayDataDataserviceBillDownloadurlQueryRequest();
        request.setBizContent("{" +
                "\"bill_type\":\"trade\"," +
                "\"bill_date\":\"2016-04-05\"" +
                "  }");
        AlipayDataDataserviceBillDownloadurlQueryResponse response = null;
        try {
            response = alipayClient.execute(request);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        if (response.isSuccess()) {
            System.out.println("调用成功");
        } else {
            System.out.println("调用失败");
        }
        return null;
    }


    /**
     * 当面付
     */

    //测试当面付支付接口
    //alipay.trade.precreate(统一收单线下交易预创建)
    // @Override
//    public Map<String, String> pay(String outTradeNo, String productId, long totalFee) throws AlipayApiException {
//       //用AliPay工具
//        AlipayTradePrecreateModel alipayTradePrecreateModel = new AlipayTradePrecreateModel();
//        AliPay.tradePrecreatePay(alipayTradePrecreateModel,"https://openapi.alipay.com/gateway.do");
//
//        //官网测试方法
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
    //
    //@Override
//    public String payNotify(String notifyXML) {
//        //
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

    //@Override
//    public Map status(String orderNo) {
//        return null;
//    }


}
