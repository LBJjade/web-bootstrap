package com.becheer.donation.model;

import java.util.Date;

/*
* Accepter : 受捐人实体类
* Creator : xiaokepu
* Date : 2017-11-23
*/
public class Accepter {
    private long id;

    private long projectTypeId;

    private long memberId;

    private String acceptorNo;

    private String name;

    private String password;

    private String mobile;

    private String idCard;

    private String address;

    private int number;

    private int role;

    private String remark;

    private int enable;

    private Date createTime;

    private long createby;

    private Date updateTime;

    private long updateBy;

    private long acceptedAmount;

    private long receivedAmount;

    private String avator;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getProjectTypeId() {
        return projectTypeId;
    }

    public void setProjectTypeId(long projectTypeId) {
        this.projectTypeId = projectTypeId;
    }

    public long getMemberId() {
        return memberId;
    }

    public void setMemberId(long memberId) {
        this.memberId = memberId;
    }

    public String getAcceptorNo() {
        return acceptorNo;
    }

    public void setAcceptorNo(String acceptorNo) {
        this.acceptorNo = acceptorNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public long getCreateby() {
        return createby;
    }

    public void setCreateby(long createby) {
        this.createby = createby;
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

    public long getAcceptedAmount() {
        return acceptedAmount;
    }

    public void setAcceptedAmount(long acceptedAmount) {
        this.acceptedAmount = acceptedAmount;
    }

    public long getReceivedAmount() {
        return receivedAmount;
    }

    public void setReceivedAmount(long receivedAmount) {
        this.receivedAmount = receivedAmount;
    }

    public String getAvator() {
        return avator;
    }

    public void setAvator(String avator) {
        this.avator = avator;
    }
}
