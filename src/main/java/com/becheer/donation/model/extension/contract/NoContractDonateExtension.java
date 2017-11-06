package com.becheer.donation.model.extension.contract;

/*
* NoContractDonateExtension
* Creator : xiaokepu
* Date : 2017-09-20
*/

import com.becheer.donation.utils.StringUtil;

import java.util.Date;

public class NoContractDonateExtension {
    private int id;

    private String memberName;

    private long amount;

    private long projectTypeId;

    private String projectTypeName;

    private long projectId;

    private String projectName;

    private String avator;

    private String ip;

    private Date createTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getAmount() {
        return StringUtil.formatMoney(amount);
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public long getProjectTypeId() {
        return projectTypeId;
    }

    public void setProjectTypeId(long projectTypeId) {
        this.projectTypeId = projectTypeId;
    }

    public String getProjectTypeName() {
        return projectTypeName;
    }

    public void setProjectTypeName(String projectTypeName) {
        this.projectTypeName = projectTypeName;
    }

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getAvator() {
        return avator;
    }

    public void setAvator(String avator) {
        this.avator = avator;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
