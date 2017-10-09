package com.becheer.donation.model.extension.progress;

import com.becheer.donation.utils.DateUtils;

import java.util.Date;

/*
* ProgressExtension
* Creator : xiaokepu
* Date : 2017-10-08
*/
public class ProgressExtension {
    private long id;

    private String title;

    private String content;

    private Date createTime;

    private int enable;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getEnable() {
        return enable;
    }

    public void setEnable(int enable) {
        this.enable = enable;
    }
}
