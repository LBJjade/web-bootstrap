package com.becheer.core.support.alipay;

import com.becheer.donation.service.SpringContextUtil;

import java.util.HashMap;
import java.util.Map;

public class AilPayHelper {
    /**
     * 构建参数
     * @param app_id
     * @param method
     * @param charset
     * @param sign_type
     * @param sign
     * @param timestamp
     * @param version
     * @param biz_content
     * @return
     */
    public static Map<String, String> buildParasMap(String app_id,String method,String charset,String sign_type,String sign,String timestamp,String version,String biz_content){
        Map<String, String> queryParas = new HashMap<String, String>();
        queryParas.put("app_id",app_id);
        queryParas.put("method",method);
        queryParas.put("charset",charset);
        queryParas.put("sign_type",sign_type);
        queryParas.put("sign",sign);
        queryParas.put("timestamp",timestamp);
        queryParas.put("version",version);
        queryParas.put("biz_content",biz_content);
        return queryParas;
    }
//
//    /**
//     * alipay.trade.query(统一收单线下交易查询)
//     * @param app_id
//     * @param method
//     * @param charset
//     * @param sign_type
//     * @param sign
//     * @param timestamp
//     * @param version
//     * @param biz_content
//     * @param out_trade_no
//     * @param trade_no
//     * @return
//     */
//    public static Map<String, String> buildParasMap(String app_id,String method,String charset,String sign_type,String sign,String timestamp,String version,String biz_content,String out_trade_no,String trade_no){
//        Map<String, String> params = new HashMap<String, String>();
//        params.put("app_id",app_id);
//        params.put("method",method);
//        params.put("charset",charset);
//        params.put("sign_type",sign_type);
//        params.put("sign",sign);
//        params.put("timestamp",timestamp);
//        params.put("version",version);
//        params.put("biz_content",biz_content);
//        params.put("out_trade_no",out_trade_no);
//        params.put("trade_no",trade_no);
//        return params;
//    }



//    public static boolean verifyAppId(Map<String, String> params){
//        AliPayConfig aliPayConfig= (AliPayConfig)SpringContextUtil.getBean("AliPayConfig");
//        String appId = params.get("app_id");
//    }




}
