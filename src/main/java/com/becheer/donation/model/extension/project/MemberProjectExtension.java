package com.becheer.donation.model.extension.project;

import com.becheer.donation.utils.StringUtil;

/*
* MemberProjectExtension 会员参与的项目
* Creator : xiaokepu
* Date : 2017-10-05
*/
public class MemberProjectExtension {
    private long id;

    private String projectName;

    private String thumbImg;

    private long projectTargetAmount;

    private long acceptedAmount;

    private long contractId;

    private long projectId;

    private long contractAmount;

    private long donatedAmount;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getThumbImg() {
        return thumbImg;
    }

    public void setThumbImg(String thumbImg) {
        this.thumbImg = thumbImg;
    }

    public String getProjectTargetAmount() {
        return StringUtil.formatMoney(projectTargetAmount);
    }

    public void setProjectTargetAmount(long projectTargetAmount) {
        this.projectTargetAmount = projectTargetAmount;
    }

    public String getAcceptedAmount() {
        return StringUtil.formatMoney(acceptedAmount);
    }

    public void setAcceptedAmount(long acceptedAmount) {
        this.acceptedAmount = acceptedAmount;
    }

    public long getContractId() {
        return contractId;
    }

    public void setContractId(long contractId) {
        this.contractId = contractId;
    }

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    public String getContractAmount() {
        return StringUtil.formatMoney(contractAmount);
    }

    public void setContractAmount(long contractAmount) {
        this.contractAmount = contractAmount;
    }

    public String getDonatedAmount() {
        return StringUtil.formatMoney(donatedAmount);
    }

    public void setDonatedAmount(long donatedAmount) {
        this.donatedAmount = donatedAmount;
    }
}
