package com.becheer.donation.model.extension.contract;

import com.becheer.donation.utils.DateUtils;
import com.becheer.donation.utils.StringUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/*
* AppropriationContractExtension
* Creator : xiaokepu
* Date : 2017-11-28
*/
public class AppropriationContractExtension {

    private long id;

    private long accepterId;

    private String contractNo;

    private String contractName;

    private long contractAmount;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date beginTime;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endTime;

    private int status;

    private String statusText;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getAccepterId() {
        return accepterId;
    }

    public void setAccepterId(long accepterId) {
        this.accepterId = accepterId;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public String getContractAmount() {
        return StringUtil.formatMoney(contractAmount);
    }

    public void setContractAmount(long contractAmount) {
        this.contractAmount = contractAmount;
    }

    public String getBeginTime() {
        return DateUtils.dateFormat(beginTime, "yyyy-MM-dd");
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return DateUtils.dateFormat(endTime, "yyyy-MM-dd");
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public int getStatus() {
        return status;
    }

    public String getStatusText() {
        switch (status) {
            case 0:
                return "编辑中";
            case 1:
                return "执行中";
            case 2:
                return "审批中";
            case 3:
                return "待签订";
            case 4:
                return "已驳回";
            case 5:
                return "调整中";
            default:
                return "";
        }
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
