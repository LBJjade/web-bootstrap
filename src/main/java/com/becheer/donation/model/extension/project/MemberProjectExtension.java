package com.becheer.donation.model.extension.project;

/*
* MemberProjectExtension 会员参与的项目
* Creator : xiaokepu
* Date : 2017-10-05
*/
public class MemberProjectExtension {
    private long id;

    private String projectName;

    private String thumbImg;

    private String projectTargetAmount;

    private String acceptedAmount;

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
        return projectTargetAmount;
    }

    public void setProjectTargetAmount(String projectTargetAmount) {
        this.projectTargetAmount = projectTargetAmount;
    }

    public String getAcceptedAmount() {
        return acceptedAmount;
    }

    public void setAcceptedAmount(String acceptedAmount) {
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
}
