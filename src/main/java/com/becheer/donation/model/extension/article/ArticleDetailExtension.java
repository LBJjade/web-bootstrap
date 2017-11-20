package com.becheer.donation.model.extension.article;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class ArticleDetailExtension {
    private long id;

    private long articleTypeId;

    private String title;

    private String summary;

    private String content;

    //创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
    private Date createTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getArticleTypeId() {
        return articleTypeId;
    }

    public void setArticleTypeId(long articleTypeId) {
        this.articleTypeId = articleTypeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
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
}
