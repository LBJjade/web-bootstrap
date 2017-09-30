package com.becheer.donation.configs;

import com.becheer.donation.utils.TaleUtils;

import java.util.Properties;

/*
* SmsConfig 短信接口Config
* Creator : xiaokepu
* Date : 2017-09-30
*/
public class SmsConfig {
    private static Properties properties = TaleUtils.getPropFromFile("src/main/resources/application.properties");

    public static String getApiUrl() {
        return properties.getProperty("sms.apiUrl");
    }

    public static String getUserName() {
        return properties.getProperty("sms.userName");
    }

    public static String getPassWord() {
        return properties.getProperty("sms.passWord");
    }
}
