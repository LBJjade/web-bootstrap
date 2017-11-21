package com.becheer.donation.model.extension.intention;

import java.util.Date;

public class IntentionDonateExtension {
    private long projectId;

    private long projectTypeId;

    private long intentionAmount;

    private String contactPhone;

    private long memberId;

    private Date createTime;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public long getMemberId() {
        return memberId;
    }

    public void setMemberId(long memberId) {
        this.memberId = memberId;
    }

    //状态 -1已作废 0已提交 1 审核中 2 确认合同中 3已驳回 4已完成
    private int enable;

    private String remark;

    private int status;

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    public long getProjectTypeId() {
        return projectTypeId;
    }

    public void setProjectTypeId(long projectTypeId) {
        this.projectTypeId = projectTypeId;
    }

    public long getIntentionAmount() {
        return intentionAmount;
    }

    public void setIntentionAmount(long intentionAmount) {
        this.intentionAmount = intentionAmount;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public int getEnable() {
        return enable;
    }

    public void setEnable(int enable) {
        this.enable = enable;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
