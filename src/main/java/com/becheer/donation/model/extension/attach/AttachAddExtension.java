package com.becheer.donation.model.extension.attach;

/**
 * 包名: com.becheer.donation.model.extension.attach
 * 文件说明: 添加合同实体
 * 创建人:amber
 * 创建日期: 2017/12/25 17:46
 * 版本:V1.0
 * Copyright © 2017 广州品清科技有限公司
 */
public class AttachAddExtension {
    private String name;

    private long size;

    private String type;

    private String url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
