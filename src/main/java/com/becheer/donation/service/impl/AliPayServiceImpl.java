package com.becheer.donation.service.impl;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradePayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.*;
import com.alipay.api.response.*;
import com.becheer.core.support.alipay.AliPay;
import com.becheer.core.support.pay.WxPayHelper;
import com.becheer.core.util.XmlUtil;
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
import java.util.HashMap;
import java.util.Map;

@Service
public class AliPayServiceImpl implements IAliPayService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AliPayServiceImpl.class);

    public AliPayServiceImpl() {
        super();
    }

    public @Autowired
    AliPayConfig aliPayConfig;

    //电脑网站支付测试
    @Override
    public Map<String, String> pagePay(HttpServletRequest httpRequest, HttpServletResponse httpResponse, String outTradeNo, String productId, long totalFee) throws ServletException, IOException {

        //返回给系统参数
        Map<String, String> AliReturn = new HashMap<>();

        //用AliPay工具
        AlipayTradePayModel alipayTradePayModel = new AlipayTradePayModel();
        alipayTradePayModel.setOutTradeNo("20150320010101001");
//        alipayTradePayModel.setOutTradeNo("outTradeNo");
        //产品销售码（签约）
        alipayTradePayModel.setProductCode("FAST_INSTANT_TRADE_PAY");
        alipayTradePayModel.setTotalAmount("88.88");
//        alipayTradePayModel.setTotalAmount("totalFee");
        //现在是写死
        //TODO
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
        AlipayClient alipayClient = new DefaultAlipayClient(aliPayConfig.getServiceUrl(), aliPayConfig.getAppId(), aliPayConfig.getPrivateKey(), aliPayConfig.getFormat(), aliPayConfig.getCharset(), aliPayConfig.getAlipayPublicKey(), aliPayConfig.getSignType()); //获得初始化的AlipayClient


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

    //处理返回参数
   //**由于同步返回的不可靠性，支付结果必须以异步通知或查询接口返回为准，不能依赖同步跳转
    @Override
    public ResponseDto handelReturn(String returnXML) {
        //同步get返回参数转成map
        // 支付宝支付结果XML
        Map<String, String> notify = XmlUtil.parseXml2Map(returnXML);
        //写入


        return new ResponseDto(200,"success",notify);
    }


    //异步通知验签
    //测试例子：https://商家网站通知地址?voucher_detail_list=[{"amount":"0.20","merchantContribute":"0.00","name":"5折券","otherContribute":"0.20","type":"ALIPAY_DISCOUNT_VOUCHER","voucherId":"2016101200073002586200003BQ4"}]&fund_bill_list=[{"amount":"0.80","fundChannel":"ALIPAYACCOUNT"},{"amount":"0.20","fundChannel":"MDISCOUNT"}]&subject=PC网站支付交易&trade_no=2016101221001004580200203978&gmt_create=2016-10-12 21:36:12&notify_type=trade_status_sync&total_amount=1.00&out_trade_no=mobile_rdm862016-10-12213600&invoice_amount=0.80&seller_id=2088201909970555&notify_time=2016-10-12 21:41:23&trade_status=TRADE_SUCCESS&gmt_payment=2016-10-12 21:37:19&receipt_amount=0.80&passback_params=passback_params123&buyer_id=2088102114562585&app_id=2016092101248425&notify_id=7676a2e1e4e737cff30015c4b7b55e3kh6& sign_type=RSA2&buyer_pay_amount=0.80&sign=***&point_amount=0.00

    //支付宝异步验签建议：
    //第一步： 在通知返回参数列表中，除去sign、sign_type两个参数外，凡是通知返回回来的参数皆是待验签的参数。
    //第二步： 将剩下参数进行url_decode, 然后进行字典排序，组成字符串，得到待签名字符串：
//    第三步： 将签名参数（sign）使用base64解码为字节码串。
//    第四步： 使用RSA的验签方法，通过签名字符串、签名参数（经过base64解码）及支付宝公钥验证签名。
//    第五步：需要严格按照如下描述校验通知数据的正确性。
    //1、商户需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
    // 2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
    // 3、校验通知中的seller_id（或者seller_email) 是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email），
    // 4、验证app_id是否为该商户本身。
    // 上述1、2、3、4有任何一个验证不通过，则表明本次通知是异常通知，务必忽略。在上述验证通过后商户必须根据支付宝不同类型的业务通知，正确的进行不同的业务处理，并且过滤重复的通知结果数据。
    // 在支付宝的业务通知中，只有交易通知状态为TRADE_SUCCESS或TRADE_FINISHED时，支付宝才会认定为买家付款成功。
    @Override
    public ResponseDto signVerfied(HttpServletRequest httpRequest, String notifyXml) {
        //异步post返回参数转成map
        Map<String, String> map = AliPay.toMap(httpRequest);


        //验证
        try {
            //验证签名
            boolean signVerifiedg = AlipaySignature.rsaCheckV1(map, "ALIPAY_PUBLIC_KEY", "GBK", "RSA2"); //调用SDK验证签名
            boolean signVerified = AlipaySignature.rsaCheckV1(map, aliPayConfig.getAlipayPublicKey(), aliPayConfig.getCharset(), aliPayConfig.getSignType()); //调用SDK验证签名
            //验证业务参数
            if (signVerified) {
                // TODO 验签成功后，按照支付结果异步通知中的描述，对支付结果中的业务内容进行二次校验，校验成功后在response中返回success并继续商户自身业务处理，校验失败返回failure
                //验证out_trade_no
                String outTradeNo = "";
                if (map.get("out_trade_no") != null) {
                    outTradeNo = map.get("outTradeNo");
                } else {
                    LOGGER.warn("支付宝支付结果通知: !!!获取统一下单数据时出现异常【out_trade_no=" + outTradeNo + "】:");
                    return new ResponseDto(501, "error");
                }


                //验证金额total_amount
                String totalAmount = "";
                if (map.get("total_amount") != null) {
                    totalAmount = map.get("total_amount");
                } else {
                    LOGGER.warn("支付宝支付结果通知: !!!获取统一下单数据时出现异常【total_amount=" + totalAmount + "】:");
                    return new ResponseDto(501, "error");
                }

                //验证seller_id
                String sellerId = "";
                if (map.get("seller_id") != null) {
                    sellerId = map.get("seller_id");
                } else {
                    LOGGER.warn("支付宝支付结果通知: !!!获取统一下单数据时出现异常【seller_id=" + sellerId + "】:");
                    return new ResponseDto(501, "error");
                }

                //验证seller_id
                String appId = "";
                if (map.get("app_id") != null) {
                    appId = map.get("app_id");
                } else {
                    LOGGER.warn("支付宝支付结果通知: !!!获取统一下单数据时出现异常【app_id=" + appId + "】:");
                    return new ResponseDto(501, "error");
                }

                return new ResponseDto(200, "success", map);
            } else {
                // TODO 验签失败则记录异常日志，并在response中返回failure.
                LOGGER.error("signVerfied", "");
                return new ResponseDto(500, "验证失败");
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
        AlipayClient alipayClient = new DefaultAlipayClient(aliPayConfig.getServiceUrl(), aliPayConfig.getAppId(), aliPayConfig.getPrivateKey(), aliPayConfig.getFormat(), aliPayConfig.getCharset(), aliPayConfig.getAlipayPublicKey(), aliPayConfig.getSignType()); //获得初始化的AlipayClient
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
        AlipayClient alipayClient = new DefaultAlipayClient(aliPayConfig.getServiceUrl(), aliPayConfig.getAppId(), aliPayConfig.getPrivateKey(), aliPayConfig.getFormat(), aliPayConfig.getCharset(), aliPayConfig.getAlipayPublicKey(), aliPayConfig.getSignType()); //获得初始化的AlipayClient
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
        AlipayClient alipayClient = new DefaultAlipayClient(aliPayConfig.getServiceUrl(), aliPayConfig.getAppId(), aliPayConfig.getPrivateKey(), aliPayConfig.getFormat(), aliPayConfig.getCharset(), aliPayConfig.getAlipayPublicKey(), aliPayConfig.getSignType()); //获得初始化的AlipayClient
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
        AlipayClient alipayClient = new DefaultAlipayClient(aliPayConfig.getServiceUrl(), aliPayConfig.getAppId(), aliPayConfig.getPrivateKey(), aliPayConfig.getFormat(), aliPayConfig.getCharset(), aliPayConfig.getAlipayPublicKey(), aliPayConfig.getSignType()); //获得初始化的AlipayClient
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
        AlipayClient alipayClient = new DefaultAlipayClient(aliPayConfig.getServiceUrl(), aliPayConfig.getAppId(), aliPayConfig.getPrivateKey(), aliPayConfig.getFormat(), aliPayConfig.getCharset(), aliPayConfig.getAlipayPublicKey(), aliPayConfig.getSignType()); //获得初始化的AlipayClient
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
