package com.becheer.donation.model.extension.message;

/*
* MessageExtension
* Creator : xiaokepu
* Date : 2017-09-27
*/

import com.becheer.donation.utils.DateUtils;

import java.util.Date;

public class MessageExtension {
    private long id;

    private long memberId;

    private String type;

    private String title;

    private String content;

    private Date createTime;

    private int status;

    public long getMemberId() { return memberId; }

    public void setMemberId(long memberId) { this.memberId = memberId; }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getId() { return id; }

    public void setId(long id) { this.id = id; }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getCreateTime() {
        return DateUtils.dateFormat(createTime);
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


}
