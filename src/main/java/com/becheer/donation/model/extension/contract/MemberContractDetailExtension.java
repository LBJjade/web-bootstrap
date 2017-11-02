package com.becheer.donation.model.extension.contract;

import com.becheer.donation.utils.DateUtils;

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

    private int status;

    private String contractNo;

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

    public String getBeginTime() {
        return DateUtils.dateFormat(beginTime,"yyyy-MM-dd");
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return DateUtils.dateFormat(endTime,"yyyy-MM-dd");
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

    public int getStatus() {
        return status;
    }

    public String getStatusText(){
        switch (status){
            case 0:
                return "编辑中";
            case 1:
                return "审批中";
            case 2:
                return "已审批";
            case 3:
                return "已驳回";
            case 4:
                return "重新编辑中";
            case 5:
                return "待签订";
            case 6:
                return "基金会已签订";
            case 7:
                return "执行中";
            case 8:
                return "已完成";
            case 9:
                return "已终止";
            case 10:
                return "已作废";
                default:
                    return "";
        }
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }
}
