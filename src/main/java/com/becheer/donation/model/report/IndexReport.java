package com.becheer.donation.model.report;

import com.becheer.donation.utils.StringUtil;

/*
* 
* Creator : xiaokepu
* Date : 
*/
public class IndexReport {
    private long receivedAmount;

    private long donateTime;

    private long benefitAmount;

    private long benefitGroup;

    public String getReceivedAmount() {
        return StringUtil.formatMoney(receivedAmount);
    }

    public void setReceivedAmount(long receivedAmount) {
        this.receivedAmount = receivedAmount;
    }

    public long getDonateTime() {
        return donateTime;
    }

    public void setDonateTime(long donateTime) {
        this.donateTime = donateTime;
    }

    public long getBenefitAmount() {
        return benefitAmount;
    }

    public void setBenefitAmount(long benefitAmount) {
        this.benefitAmount = benefitAmount;
    }

    public long getBenefitGroup() {
        return benefitGroup;
    }

    public void setBenefitGroup(long benefitGroup) {
        this.benefitGroup = benefitGroup;
    }
}
