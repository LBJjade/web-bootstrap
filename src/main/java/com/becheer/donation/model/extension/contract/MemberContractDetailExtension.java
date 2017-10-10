package com.becheer.donation.model.extension.contract;

import java.util.Date;

/*
* MemberContractDetailExtension
* Creator : xiaokepu
* Date : 2017-10-10
*/
public class MemberContractDetailExtension {

    private long id;

    private String contractName;

    private long contractAmount;

    private long donatedAmount;

    private Date beginTime;

    private Date endTime;

    private Date signTime;

    private int enable;

    private String status;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public long getContractAmount() {
        return contractAmount;
    }

    public void setContractAmount(long contractAmount) {
        this.contractAmount = contractAmount;
    }

    public long getDonatedAmount() {
        return donatedAmount;
    }

    public void setDonatedAmount(long donatedAmount) {
        this.donatedAmount = donatedAmount;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getSignTime() {
        return signTime;
    }

    public void setSignTime(Date signTime) {
        this.signTime = signTime;
    }

    public int getEnable() {
        return enable;
    }

    public void setEnable(int enable) {
        this.enable = enable;
    }

    public String getStatus() {
        switch (enable){
            case 0:
                return "已作废";
            case 1:
                return "执行中";
            case 2:
                return "已终止";
            case 3:
                return "已结束";
            default:return "";
        }
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
