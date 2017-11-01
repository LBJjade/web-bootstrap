package com.becheer.donation.model.extension.appeal;

import com.becheer.donation.model.base.BaseModel;

public class AppealDetailExtension extends BaseModel{

    private Long contractId;
    private Long projectId;
    private Long memberId;
    private String appealTitle;
    private String appealContent;
    private String appealMethod;
    private int appealType;
    private int enable;
    private int status;

    public Long getContractId() {
        return contractId;
    }

    public void setContractId(Long contractId) {
        this.contractId = contractId;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getAppealTitle() {
        return appealTitle;
    }

    public void setAppealTitle(String appealTitle) {
        this.appealTitle = appealTitle;
    }

    public String getAppealContent() {
        return appealContent;
    }

    public void setAppealContent(String appealContent) {
        this.appealContent = appealContent;
    }

    public String getAppealMethod() {
        return appealMethod;
    }

    public void setAppealMethod(String appealMethod) {
        this.appealMethod = appealMethod;
    }

    public int getAppealType() {
        return appealType;
    }

    public void setAppealType(int appealType) {
        this.appealType = appealType;
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
}
