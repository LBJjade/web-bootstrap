package com.becheer.donation.configs;

import com.becheer.donation.utils.TaleUtils;

import java.util.Properties;

/*
* SmsConfig 短信接口Config
* Creator : xiaokepu
* Date : 2017-09-30
*/
public class SmsConfig {
//    private static Properties properties = TaleUtils.getPropFromFile("classpath*:/application.properties");

//    public static String getApiUrl() {
//        return properties.getProperty("sms.apiUrl");
//    }
//
//    public static String getUserName() {
//        return properties.getProperty("sms.userName");
//    }
//
//    public static String getPassWord() {
//        return properties.getProperty("sms.passWord");
//    }

    public static String getApiUrl() {
        return "http://www.17int.cn/xxsmsweb/smsapi/send.json";
    }

    public static String getUserName() {
        return "s11050003";
    }

    public static String getPassWord() {
        return "qwer1234";
    }
}
