package com.becheer.donation.model.extension.attach;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * 包名: com.becheer.donation.model.extension.attach
 * 文件说明: AttachExtension
 * 创建人:amber
 * 创建日期: 2017/12/7 18:48
 * 版本:V1.0
 * Copyright © 2017 广州品清科技有限公司
 */
public class AttachExtension {
    private long id;

    private String name;

    private String refTable;

    private long refId;

    private String attachUrl;

    private String remark;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date updateTime;

    private String attachType;

    private long attachSize;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRefTable() {
        return refTable;
    }

    public void setRefTable(String refTable) {
        this.refTable = refTable;
    }

    public long getRefId() {
        return refId;
    }

    public void setRefId(long refId) {
        this.refId = refId;
    }

    public String getAttachUrl() {
        return attachUrl;
    }

    public void setAttachUrl(String attachUrl) {
        this.attachUrl = attachUrl;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getAttachType() {
        return attachType;
    }

    public void setAttachType(String attachType) {
        this.attachType = attachType;
    }

    public long getAttachSize() {
        return attachSize;
    }

    public void setAttachSize(long attachSize) {
        this.attachSize = attachSize;
    }
}
