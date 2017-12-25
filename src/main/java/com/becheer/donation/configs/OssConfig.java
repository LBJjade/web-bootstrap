package com.becheer.donation.configs;

/*
* OssConfig
* Creator : xiaokepu
* Date : 2017-10-24
*/

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="oss")
public class OssConfig {

    private String endpoint;

    private String accessKeyId;

    private String accessKeySecret;

    private String bucketName;

    private String avatorPath;

    private String idCardPath;

    private String licensePath;

    private String attachPath;

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    public String getAccessKeySecret() {
        return accessKeySecret;
    }

    public void setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getAvatorPath() {
        return avatorPath;
    }

    public void setAvatorPath(String avatorPath) {
        this.avatorPath = avatorPath;
    }

    public String getIdCardPath() {
        return idCardPath;
    }

    public void setIdCardPath(String idCardPath) {
        this.idCardPath = idCardPath;
    }

    public String getLicensePath() {
        return licensePath;
    }

    public void setLicensePath(String licensePath) {
        this.licensePath = licensePath;
    }

    public String getAttachPath() {
        return attachPath;
    }

    public void setAttachPath(String attachPath) {
        this.attachPath = attachPath;
    }
}
