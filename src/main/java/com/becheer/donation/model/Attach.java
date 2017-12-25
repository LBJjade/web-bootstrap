package com.becheer.donation.model;

import java.util.Date;

/**
 * 包名: com.becheer.donation.model
 * 文件说明: 附件实体类
 * 创建人:amber
 * 创建日期: 2017/12/25 17:17
 * 版本:V1.0
 * Copyright © 2017 广州品清科技有限公司
 */

public class Attach {
    private long id;

    private String name;

    private String refTable;

    private long refId;

    private String url;

    private String attachUrl;

    private int enable;

    private String remark;

    private Date createTime;

    private long createBy;

    private Date updateTime;

    private long updateBy;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAttachUrl() {
        return attachUrl;
    }

    public void setAttachUrl(String attachUrl) {
        this.attachUrl = attachUrl;
    }

    public int getEnable() {
        return enable;
    }

    public void setEnable(int enable) {
        this.enable = enable;
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

    public long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(long createBy) {
        this.createBy = createBy;
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
