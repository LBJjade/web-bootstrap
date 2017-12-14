package com.becheer.donation.configs;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/*
* SmsConfig 短信接口Config
* Creator : xiaokepu
* Date : 2017-09-30
*/

@Component
@ConfigurationProperties(prefix="sms")
public class SmsConfig {

    private String apiUrl;

    private String userName;

    private String passWord;

    public String getApiUrl() {
        return apiUrl;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}
