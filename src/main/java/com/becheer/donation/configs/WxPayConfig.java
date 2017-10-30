package com.becheer.donation.configs;

/*
* WxPayConfig
*/

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="wxpay")
public class WxPayConfig {

    private String appId;

    private String apiSecret;

    private String mchId;

    private String spBillCreateIP;

    private String notifyURL;

    private String tradeType;

    private String deviceInfo;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getApiSecret() {
        return apiSecret;
    }

    public void setAppSecret(String apiSecret) {
        this.apiSecret = apiSecret;
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getSpBillCreateIP() {
        return spBillCreateIP;
    }

    public void setSpBillCreateIP(String spBillCreateIP) {
        this.spBillCreateIP = spBillCreateIP;
    }

    public String getNotifyURL() {
        return notifyURL;
    }

    public void setNotifyURL(String notifyURL) {
        this.notifyURL = notifyURL;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public String getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(String deviceInfo) {
        this.deviceInfo = deviceInfo;
    }
}
