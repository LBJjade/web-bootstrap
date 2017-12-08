package com.becheer.donation.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 包名: com.becheer.core.support.alipay
 * 文件说明: aliPay配置类
 * 创建人:LBJ
 * 创建日期: 2017/12/7 16:09
 * 版本:V1.0
 * Copyright  2017 广州品清科技有限公司
 */

@Component
@ConfigurationProperties(prefix="aliPay")
public class AliPayConfig {
    private String privateKey;

    private String alipayPublicKey;

    private String appId;

    private String serviceUrl;

    private String charset;

    private String signType;

    private String format;

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public String getAlipayPublicKey() {
        return alipayPublicKey;
    }

    public void setAlipayPublicKey(String alipayPublicKey) {
        this.alipayPublicKey = alipayPublicKey;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getServiceUrl() {
        return serviceUrl;
    }

    public void setServiceUrl(String serviceUrl) {
        this.serviceUrl = serviceUrl;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }
}
