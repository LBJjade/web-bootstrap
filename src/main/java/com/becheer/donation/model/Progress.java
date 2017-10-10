package com.becheer.donation.model;

/*
* Progress
* Creator : xiaokepu
* Date : 2017-10-09
*/

import java.util.Date;

public class Progress {
    private long id;

    private String refTable;

    private long refRecordId;

    private String title;

    private String content;

    private int enable;

    private Date createTime;

    private long createMemberId;

    private long createBy;

    private Date updateTime;

    private long updateBy;

    private String remark;

    private String recordStatus;

    private String module;

    private int type;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRefTable() {
        return refTable;
    }

    public void setRefTable(String refTable) {
        this.refTable = refTable;
    }

    public long getRefRecordId() {
        return refRecordId;
    }

    public void setRefRecordId(long refRecordId) {
        this.refRecordId = refRecordId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getEnable() {
        return enable;
    }

    public void setEnable(int enable) {
        this.enable = enable;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(long createBy) {
        this.createBy = createBy;
    }

    public long getCreateMemberId() {
        return createMemberId;
    }

    public void setCreateMemberId(long createMemberId) {
        this.createMemberId = createMemberId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public long getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(long updateBy) {
        this.updateBy = updateBy;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRecordStatus() {
        return recordStatus;
    }

    public void setRecordStatus(String recordStatus) {
        this.recordStatus = recordStatus;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}