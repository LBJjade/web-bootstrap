package com.becheer.donation.model;

/*
* Sms 短信实体类
* Creator : xiaokepu
* Date : 2017-09-12
*/


import java.util.Date;

public class Sms {
    private long id;

    private long smsTemplateId;

    private String mobile;

    private String code;

    private String text;

    private String reply;

    private String smsType;

    private Date sendTime;

    private Date invalidTime;

    private String sendStatus;

    private String sendId;

    private String smsCredit;

    private String sendErrorCode;

    private String sendErrorMessage;

    //验证码状态 0 未验证 1 已验证
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

    public long getSmsTemplateId() {
        return smsTemplateId;
    }

    public void setSmsTemplateId(long smsTemplateId) {
        this.smsTemplateId = smsTemplateId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public String getSmsType() {
        return smsType;
    }

    public void setSmsType(String smsType) {
        this.smsType = smsType;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public Date getInvalidTime() {
        return invalidTime;
    }

    public void setInvalidTime(Date invalidTime) {
        this.invalidTime = invalidTime;
    }

    public String getSendStatus() {
        return sendStatus;
    }

    public void setSendStatus(String sendStatus) {
        this.sendStatus = sendStatus;
    }

    public String getSendId() {
        return sendId;
    }

    public void setSendId(String sendId) {
        this.sendId = sendId;
    }

    public String getSmsCredit() {
        return smsCredit;
    }

    public void setSmsCredit(String smsCredit) {
        this.smsCredit = smsCredit;
    }

    public String getSendErrorCode() {
        return sendErrorCode;
    }

    public void setSendErrorCode(String sendErrorCode) {
        this.sendErrorCode = sendErrorCode;
    }

    public String getSendErrorMessage() {
        return sendErrorMessage;
    }

    public void setSendErrorMessage(String sendErrorMessage) {
        this.sendErrorMessage = sendErrorMessage;
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
