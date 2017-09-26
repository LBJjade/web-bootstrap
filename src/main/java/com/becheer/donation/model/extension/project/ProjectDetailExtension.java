package com.becheer.donation.model.extension.project;

/*
* ProjectDetailExtension 项目明细页面实体
* Creator : xiaokepu
* Date : 2017-09-25
*/

import java.util.Date;

public class ProjectDetailExtension {
    private long id;

    private long projectTypeId;

    private String projectName;

    private Date startTime;

    private Date endTime;

    private int longTerm;

    private String content;

    private long projectTargetAmount;

    private long acceptedAmount;

    private String thumbImg;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getProjectTypeId() {
        return projectTypeId;
    }

    public void setProjectTypeId(long projectTypeId) {
        this.projectTypeId = projectTypeId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public int getLongTerm() {
        return longTerm;
    }

    public void setLongTerm(int longTerm) {
        this.longTerm = longTerm;
    }

    public long getProjectTargetAmount() {
        return projectTargetAmount;
    }

    public void setProjectTargetAmount(long projectTargetAmount) {
        this.projectTargetAmount = projectTargetAmount;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getAcceptedAmount() {
        return acceptedAmount;
    }

    public void setAcceptedAmount(long acceptedAmount) {
        this.acceptedAmount = acceptedAmount;
    }

    public String getThumbImg() {
        return thumbImg;
    }

    public void setThumbImg(String thumbImg) {
        this.thumbImg = thumbImg;
    }
}
