package com.becheer.core.support.pay;

import com.baomidou.mybatisplus.toolkit.IdWorker;
import com.becheer.donation.configs.WxPayConfig;
import com.becheer.donation.service.SpringContextUtil;
import com.becheer.donation.utils.DateUtils;
import org.apache.commons.codec.Charsets;
import org.apache.commons.lang3.StringUtils;
import com.becheer.core.util.HashUtil;
import com.becheer.core.util.XmlUtil;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class WxPayHelper {
    /**
     * 构建参数
     *
     * @param appid
     * @param sub_appid
     * @param mch_id
     * @param sub_mch_id
     * @param device_info
     * @param body
     * @param detail
     * @param attach
     * @param out_trade_no
     * @param total_fee
     * @param spbill_create_ip
     * @param auth_code
     * @param wxPayApiSecret   微信支付API密钥(https://pay.weixin.qq.com/index.php/core/cert/api_cert)
     * @return
     */
    public static Map<String, String> buildParasMap(String appid, String sub_appid, String mch_id, String sub_mch_id,
                                                    String device_info, String body, String detail, String attach, String out_trade_no, String total_fee,
                                                    String spbill_create_ip, String auth_code, String wxPayApiSecret) {
        Map<String, String> queryParas = new HashMap<String, String>();
        queryParas.put("appid", appid);
        queryParas.put("sub_appid", sub_appid);
        queryParas.put("mch_id", mch_id);
        queryParas.put("sub_mch_id", sub_mch_id);
        queryParas.put("device_info", device_info);
        queryParas.put("nonce_str", String.valueOf(System.currentTimeMillis()));
        queryParas.put("body", body);
        queryParas.put("detail", detail);
        queryParas.put("attach", attach);
        queryParas.put("out_trade_no", out_trade_no);
        queryParas.put("total_fee", total_fee);
        queryParas.put("spbill_create_ip", spbill_create_ip);
        queryParas.put("auth_code", auth_code);
        String sign = WxPayHelper.createSign(queryParas, wxPayApiSecret);
        queryParas.put("sign", sign);
        return queryParas;
    }

    /**
     * 封装查询请求参数 参考代码
     *
     * @param appid
     * @param sub_appid
     * @param mch_id
     * @param sub_mch_id
     * @param transaction_id
     * @param out_trade_no
     * @param wxPayApiSecret
     * @return
     */
    public static Map<String, String> buildParasMap(String appid, String sub_appid, String mch_id, String sub_mch_id,
                                                    String transaction_id, String out_trade_no, String wxPayApiSecret) {
        Map<String, String> params = new HashMap<String, String>();

        params.put("appid", appid);
        params.put("sub_appid", sub_appid);
        params.put("mch_id", mch_id);
        params.put("sub_mch_id", sub_mch_id);
        params.put("transaction_id", transaction_id);
        params.put("out_trade_no", out_trade_no);

        return buildSignAfterParasMap(params, wxPayApiSecret);
    }


    public static Map<String, String> buildUnifiedOrderParasMap(String out_trade_no, String product_id, String total_fee) {
        Map<String, String> params = new HashMap<String, String>();
        WxPayConfig config = (WxPayConfig) SpringContextUtil.getBean("wxPayConfig");
        String appid = config.getAppId();
        String mch_id = config.getMchId();
        String device_info = config.getDeviceInfo();
        String body = "广东省世纪爱心慈善基金会";
        String detail = "";
        String attach = "";
        String spbill_create_ip = config.getSpBillCreateIP();
        String notify_url = config.getNotifyURL();
        String trade_type = config.getTradeType();
        String wxPayApiSecret = config.getApiSecret();
        Date date = new Date();
        String timeStart = DateUtils.dateFormat(date, "yyyyMMddHHmmss");
        String timeExpire = DateUtils.dateFormat(new Date(date.getTime() + 300000), "yyyyMMddHHmmss");


        params.put("appid", appid);
        params.put("mch_id", mch_id);
        params.put("device_info", device_info);
        params.put("body", body);
        params.put("detail", detail);
        params.put("attach", attach);
        params.put("fee_type", "CNY");
        params.put("out_trade_no", out_trade_no);

        params.put("total_fee", total_fee);
        params.put("spbill_create_ip", spbill_create_ip);
        params.put("notify_url", notify_url);
        params.put("trade_type", trade_type);
        params.put("product_id", product_id);
        params.put("time_start", timeStart);
        params.put("time_expire", timeExpire);

        return buildSignAfterParasMap(params, wxPayApiSecret);
    }

    /**
     * 构建统一下单参数
     *
     * @param appid            AppID
     * @param sub_appid        否
     * @param mch_id           商户号
     * @param sub_mch_id       服务商模式下必须
     * @param device_info      否
     * @param body             商品简单描述
     * @param detail           否
     * @param attach           否
     * @param out_trade_no     订单号
     * @param total_fee        支付总额
     * @param spbill_create_ip 终端IP
     * @param wxPayApiSecret   AppSecret
     * @param notify_url       回调页面地址
     * @param trade_type       交易类型
     * @param product_id       扫码支付必传
     * @return 签名后准备提交带微信支付接口的参数列表
     */
    public static Map<String, String> buildUnifiedOrderParasMap(String appid, String sub_appid, String mch_id,
                                                                String sub_mch_id, String device_info, String body, String detail, String attach, String out_trade_no,
                                                                String total_fee, String spbill_create_ip, String notify_url, String trade_type, String wxPayApiSecret,
                                                                String product_id) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("appid", appid);
        // params.put("sub_appid", sub_appid);
        params.put("mch_id", mch_id);
        // params.put("sub_mch_id", sub_mch_id);
        params.put("device_info", device_info);
        params.put("body", body);
        params.put("detail", detail);
        params.put("attach", attach);
        params.put("fee_type", "CNY");
        params.put("out_trade_no", out_trade_no);

        params.put("total_fee", total_fee);
        params.put("spbill_create_ip", spbill_create_ip);
        params.put("notify_url", notify_url);
        params.put("trade_type", trade_type);
        params.put("product_id", product_id);

        return buildSignAfterParasMap(params, wxPayApiSecret);
    }

    /**
     * 构建短链接参数
     *
     * @param appid
     * @param sub_appid
     * @param mch_id
     * @param sub_mch_id
     * @param long_url
     * @param wxPayApiSecret
     * @return
     */
    public static Map<String, String> buildShortUrlParasMap(String appid, String sub_appid, String mch_id,
                                                            String sub_mch_id, String long_url, String wxPayApiSecret) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("appid", appid);
        params.put("sub_appid", sub_appid);
        params.put("mch_id", mch_id);
        params.put("sub_mch_id", sub_mch_id);
        params.put("long_url", long_url);

        return buildSignAfterParasMap(params, wxPayApiSecret);

    }

    /**
     * 组装签名的字段
     *
     * @param params     参数
     * @param urlEncoder 是否urlEncoder
     * @return String
     */
    public static String packageSign(Map<String, String> params, boolean urlEncoder) {
        // 先将参数以其参数名的字典序升序进行排序
        TreeMap<String, String> sortedParams = new TreeMap<String, String>(params);
        // 遍历排序后的字典，将所有参数按"key=value"格式拼接在一起
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for (Entry<String, String> param : sortedParams.entrySet()) {
            String value = param.getValue();
            if (StringUtils.isBlank(value)) {
                continue;
            }
            if (first) {
                first = false;
            } else {
                sb.append("&");
            }
            sb.append(param.getKey()).append("=");
            if (urlEncoder) {
                try {
                    value = urlEncode(value);
                } catch (UnsupportedEncodingException e) {
                }
            }
            sb.append(value);
        }
        return sb.toString();
    }

    /**
     * urlEncode
     *
     * @param src 微信参数
     * @return String
     * @throws UnsupportedEncodingException 编码错误
     */
    public static String urlEncode(String src) throws UnsupportedEncodingException {
        return URLEncoder.encode(src, Charsets.UTF_8.name()).replace("+", "%20");
    }

    /**
     * 构建签名之后的参数
     *
     * @param params
     * @param wxPayApiSecret
     * @return Map
     */
    public static Map<String, String> buildSignAfterParasMap(Map<String, String> params, String wxPayApiSecret) {
        params.put("nonce_str", IdWorker.get32UUID());
        String sign = WxPayHelper.createSign(params, wxPayApiSecret);
        params.put("sign", sign);
        return params;
    }

    /**
     * 生成签名
     *
     * @param params         参数
     * @param wxPayApiSecret 支付密钥
     */
    public static String createSign(Map<String, String> params, String wxPayApiSecret) {
        // 生成签名前先去除sign
        params.remove("sign");
        String stringA = packageSign(params, false);
        String stringSignTemp = stringA + "&key=" + wxPayApiSecret;
        return HashUtil.md5(stringSignTemp).toUpperCase();
    }


    /**
     * 验证AppID和MchID
     *
     * @param params 参数
     * @return {boolean}
     */
    public static boolean verifyAppIdAndMchId(Map<String, String> params) {
        WxPayConfig config = (WxPayConfig) SpringContextUtil.getBean("wxPayConfig");
        String appId = params.get("appid");
        String mchId = params.get("mch_id");
        String localAppId = config.getAppId();
        String localMchId = config.getMchId();
        return appId.equals(localAppId) && mchId.equals(localMchId);
    }

    /**
     * 支付异步通知时校验sign
     *
     * @param params 参数
     * @return {boolean}
     */
    public static boolean verifyNotify(Map<String, String> params) {
        WxPayConfig config = (WxPayConfig) SpringContextUtil.getBean("wxPayConfig");
        String wxPayApiSecret = config.getApiSecret();
        String sign = params.get("sign");
        String localSign = WxPayHelper.createSign(params, wxPayApiSecret);
        return sign.equals(localSign);
    }

    /**
     * 支付异步通知时校验sign
     *
     * @param params         参数
     * @param wxPayApiSecret 支付密钥
     * @return {boolean}
     */
    public static boolean verifyNotify(Map<String, String> params, String wxPayApiSecret) {
        String sign = params.get("sign");
        String localSign = WxPayHelper.createSign(params, wxPayApiSecret);
        return sign.equals(localSign);
    }

    /**
     * 判断接口返回的code是否是SUCCESS
     *
     * @param return_code、result_code
     * @return
     */
    public static boolean codeIsOK(String return_code) {
        return StringUtils.isNotBlank(return_code) && "SUCCESS".equals(return_code);
    }

    /**
     * 微信下单map to xml
     *
     * @param params 参数
     * @return String
     */
    public static String toXml(Map<String, String> params) {
        StringBuilder xml = new StringBuilder();
        xml.append("<xml>");
        for (Entry<String, String> entry : params.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            // 略过空值
            if (StringUtils.isBlank(value))
                continue;
            xml.append("<").append(key).append(">");
            xml.append(entry.getValue());
            xml.append("</").append(key).append(">");
        }
        xml.append("</xml>");
        return xml.toString();
    }

    /**
     * 微信下单map to xml
     *
     * @param value 需要增加CDATA的值
     * @return String
     */
    public static String toCDATAValue(String value) {
        StringBuilder xml = new StringBuilder();
        xml.append("<![CDATA[");
        xml.append(value);
        xml.append("]]>");
        return xml.toString();
    }

    /**
     * 针对支付的xml，没有嵌套节点的简单处理
     *
     * @param xmlStr xml字符串
     * @return map集合
     */
    @SuppressWarnings("unchecked")
    public static Map<String, String> xmlToMap(String xmlStr) {
        return XmlUtil.parseXml2Map(xmlStr);
    }

    /**
     * 替换url中的参数
     *
     * @param str
     * @param regex
     * @param args
     * @return
     */
    public static String replace(String str, String regex, String... args) {
        int length = args.length;
        for (int i = 0; i < length; i++) {
            str = str.replaceFirst(regex, args[i]);
        }
        return str;
    }
}
