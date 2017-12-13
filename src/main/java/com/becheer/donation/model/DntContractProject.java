package com.becheer.donation.model;

import org.springframework.stereotype.Component;

/**
 * 包名: com.becheer.donation.model
 * 文件说明: 描述当文件的用途
 * 创建人:LBJ
 * 创建日期: 2017/12/12 15:24
 * 版本:V1.0
 * Copyright  2017 广州品清科技有限公司
 */
@Component
public class DntContractProject {

    private Long id;

    private Long contractId;

    private Long projectId;

    private Integer contractAmount;

    private Integer donatedAmount;

    private Integer acceptedAmount;

    private Integer allocateAmoumt;

    private String remark;

    private Integer enable;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Integer getContractAmount() {
        return contractAmount;
    }

    public void setContractAmount(Integer contractAmount) {
        this.contractAmount = contractAmount;
    }

    public Integer getDonatedAmount() {
        return donatedAmount;
    }

    public void setDonatedAmount(Integer donatedAmount) {
        this.donatedAmount = donatedAmount;
    }

    public Integer getAcceptedAmount() {
        return acceptedAmount;
    }

    public void setAcceptedAmount(Integer acceptedAmount) {
        this.acceptedAmount = acceptedAmount;
    }

    public Integer getAllocateAmoumt() {
        return allocateAmoumt;
    }

    public void setAllocateAmoumt(Integer allocateAmoumt) {
        this.allocateAmoumt = allocateAmoumt;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getEnable() {
        return enable;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
    }
}
