package com.becheer.donation.model.extension.allocate;

import com.becheer.donation.utils.DateUtils;
import com.becheer.donation.utils.StringUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/*
* AllocatePlanExtension
* Creator : xiaokepu
* Date : 2017-11-29
*/
public class AllocatePlanExtension {
    private long id;

    private String title;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date allocateDate;

    private long amount;

    private long allocateAmount;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date deadLine;

    private int enable;

    private int status;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getAllocateDate() {
        return allocateDate;
    }

    public void setAllocateDate(Date allocateDate) {
        this.allocateDate = allocateDate;
    }

    public String getAmount() {
        return StringUtil.formatMoney(amount);
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public String getAllocateAmount() {
        return StringUtil.formatMoney(allocateAmount);
    }

    public void setAllocateAmount(long allocateAmount) {
        this.allocateAmount = allocateAmount;
    }

    public Date getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(Date deadLine) {
        this.deadLine = deadLine;
    }

    public int getEnable() {
        return enable;
    }

    public void setEnable(int enable) {
        this.enable = enable;
    }

    public String getStatus() {
        switch (status) {
            case 0:
                return "未批资";
            case 1:
                return "已批资";
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

    public void setStatus(int status) {
        this.status = status;
    }
}
