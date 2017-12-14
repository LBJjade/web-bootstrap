package com.becheer.donation.model;

/**
 * 包名: com.becheer.donation.model
 * 文件说明: 区域信息类
 * 创建人:amber
 * 创建日期: 2017/12/12 16:59
 * 版本:V1.0
 * Copyright © 2017 广州品清科技有限公司
 */
public class Area {
    private long id;

    private String name;

    private long parentId;

    private int type;

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

    public long getParentId() {
        return parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
