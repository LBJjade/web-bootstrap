package com.becheer.donation.model;

/*
* SmsTemplate 短信模板类
* Creator : xiaokepu
* Date : 2017-09-12
*/


import java.sql.Date;

public class SmsTemplate {
    private long id;

    private String smsAppId;

    private String smsSignature;

    private String smsUrl;

    private String smsProject;

    private String smsProjectName;

    private String content;

    private int enable;

    private Date createTime;

    private long createBy;

    private Date updateTime;

    private long updateBy;

    private String remark;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSmsAppId() {
        return smsAppId;
    }

    public void setSmsAppId(String smsAppId) {
        this.smsAppId = smsAppId;
    }

    public String getSmsSignature() {
        return smsSignature;
    }

    public void setSmsSignature(String smsSignature) {
        this.smsSignature = smsSignature;
    }

    public String getSmsUrl() {
        return smsUrl;
    }

    public void setSmsUrl(String smsUrl) {
        this.smsUrl = smsUrl;
    }

    public String getSmsProject() {
        return smsProject;
    }

    public void setSmsProject(String smsProject) {
        this.smsProject = smsProject;
    }

    public String getSmsProjectName() {
        return smsProjectName;
    }

    public void setSmsProjectName(String smsProjectName) {
        this.smsProjectName = smsProjectName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getEnable() {
        return enable;
    }

    public void setEnable(int enable) {
        this.enable = enable;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(long createBy) {
        this.createBy = createBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public long getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(long updateBy) {
        this.updateBy = updateBy;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
