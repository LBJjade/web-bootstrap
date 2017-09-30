package com.becheer.donation.utils;

import java.nio.charset.Charset;
import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.becheer.donation.configs.SmsConfig;
import com.becheer.donation.model.extension.sms.SmsContent;
import com.becheer.donation.model.extension.sms.SmsResponse;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.transform.Result;

/*
* SmsUtil 短信工具类
* Creator : xiaokepu
* Date : 2017-09-30
*/

public class SmsUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(SmsUtil.class);

    public static String requestUrl = SmsConfig.getApiUrl();
    public static String encoding = "UTF-8";

    /**
     * 发送短信
     * @param mobile
     * @param content
     */
    public static SmsResponse SendSms(String mobile, String content) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        LOGGER.error("开始发送短信");
        try {
            HttpPost httppost = new HttpPost(requestUrl);
            SmsContent smsDetail = new SmsContent();
            smsDetail.setAccount(SmsConfig.getUserName());
            smsDetail.setPassword(HashUtil.MD5(SmsConfig.getPassWord()).toUpperCase());
            smsDetail.setMobile(mobile);
            smsDetail.setContent(content);
            smsDetail.setExtNo("");
            smsDetail.setRequestId(String.valueOf(System.nanoTime()));
            ObjectMapper mapper = new ObjectMapper();
            mapper.setSerializationInclusion(Include.NON_NULL);
            String jsonContent = "";
            String respContent = "";
            jsonContent = mapper.writeValueAsString(smsDetail);
            httppost.addHeader("Content-type", "application/json; charset=utf-8");
            httppost.setHeader("Accept", "application/json");
            httppost.setEntity(new StringEntity(jsonContent, Charset.forName(encoding)));
            CloseableHttpResponse response = httpclient.execute(httppost);
            try {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    respContent = EntityUtils.toString(entity, encoding);
                    SmsResponse result= JSON.parseObject(respContent,SmsResponse.class);
                    return result;
                }
            } finally {
                response.close();
            }
            LOGGER.error("短信发送成功");
        } catch (Exception e) {
            LOGGER.error("SendSms", e);
        } finally {
            try {
                httpclient.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
