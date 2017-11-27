package com.becheer.donation.model.extension.accepter;

/*
* AccepterInfoExtension
* Creator : xiaokepu
* Date : 2017-11-27
*/
public class AccepterInfoExtension {
    private long id;

    private long memberId;

    private String accepterNo;

    private String name;

    private String mobile;

    private String idCard;

    private String address;

    private String avator;

    private long receivedAmount;

    private int enable;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getMemberId() {
        return memberId;
    }

    public void setMemberId(long memberId) {
        this.memberId = memberId;
    }

    public String getAccepterNo() {
        return accepterNo;
    }

    public void setAccepterNo(String accepterNo) {
        this.accepterNo = accepterNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getAvator() {
        return avator;
    }

    public void setAvator(String avator) {
        this.avator = avator;
    }

    public long getReceivedAmount() {
        return receivedAmount;
    }

    public void setReceivedAmount(long receivedAmount) {
        this.receivedAmount = receivedAmount;
    }

    public int getEnable() {
        return enable;
    }

    public void setEnable(int enable) {
        this.enable = enable;
    }
}
