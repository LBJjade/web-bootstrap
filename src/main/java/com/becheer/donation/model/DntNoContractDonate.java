package com.becheer.donation.model;

import net.sf.jsqlparser.expression.DateTimeLiteralExpression;

import java.util.Date;

public class DntNoContractDonate {

    private Integer id;          // id_ 直接捐赠标识
    private Integer projectTypeId;          // projectTypeId bigint null comment '项目类型标识【,.$(projectTypeName)@(dntProjectType)】',
    private Integer projectId;             // projectId bigint null comment '项目标识【,.!】',
    private String noContractDonateNo;        // noContractDonateNo varchar(20) null comment '直接捐赠编号【,.!】',
    private Long memberId;        // memberId bigint null comment '会员标识【,.!】',
    private Integer amount;        // amount bigint default '0' null comment '捐赠金额【,.*】',
    private Integer donatedAmount;        // donatedAmount bigint default '0' null comment '已捐赠金额【,.!】',
    private Integer allocateAmount;        // allocateAmount bigint default '0' null comment '已批资金额【,.!】',
    private String remark;        // remark varchar(1000) null comment '备注【,.】',
    private Integer enable;        // enable int null comment '状态',
    private Date createTime;        // createTime datetime null comment '创建时间',
    private Integer createBy;        // createBy bigint null comment '创建人',
    private Date updateTime;        // updateTime datetime null comment '修改时间',
    private Integer updateBy;        // updateBy bigint null comment '修改人',
    private String ip;        // ip varchar(64) null comment 'ip',
    private Integer appropriationContractId;        // appropriationContractId bigint null comment '批资资金用途标识【,.*$(contractName)@(dntAppropriationContract)】'

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProjectTypeId() {
        return projectTypeId;
    }

    public void setProjectTypeId(Integer projectTypeId) {
        this.projectTypeId = projectTypeId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getNoContractDonateNo() {
        return noContractDonateNo;
    }

    public void setNoContractDonateNo(String noContractDonateNo) {
        this.noContractDonateNo = noContractDonateNo;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getDonatedAmount() {
        return donatedAmount;
    }

    public void setDonatedAmount(Integer donatedAmount) {
        this.donatedAmount = donatedAmount;
    }

    public Integer getAllocateAmount() {
        return allocateAmount;
    }

    public void setAllocateAmount(Integer allocateAmount) {
        this.allocateAmount = allocateAmount;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getAppropriationContractId() {
        return appropriationContractId;
    }

    public void setAppropriationContractId(Integer appropriationContractId) {
        this.appropriationContractId = appropriationContractId;
    }
}
