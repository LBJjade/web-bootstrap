package com.becheer.donation.model.extension.payment;

import com.becheer.donation.utils.DateUtils;
import com.becheer.donation.utils.StringUtil;

import java.util.Date;

/*
* PaymentPlanExtension
* Creator : xiaokepu
* Date : 2017-10-11
*/
public class PaymentPlanExtension {
    private long id;

    private String title;

    private Date paymentDate;

    private long amount;

    private long receivedAmount;

    private Date deadLine;

    private int enable;

    private int status;

    private String statusText;

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

    public String getPaymentDate() {
        return DateUtils.dateFormat(paymentDate,"yyyy-MM-dd");
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getAmount() {
        return StringUtil.formatMoney(amount);
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public String getReceivedAmount() {
        return StringUtil.formatMoney(receivedAmount);
    }

    public void setReceivedAmount(long receivedAmount) {
        this.receivedAmount = receivedAmount;
    }

    public String getDeadLine() {
        return DateUtils.dateFormat(paymentDate,"yyyy-MM-dd");
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStatusText() {
        switch(status){
            case 0:
                return "未捐赠";
            case 1:
                return "已捐赠";
            default:
                return "";
        }
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }
}
