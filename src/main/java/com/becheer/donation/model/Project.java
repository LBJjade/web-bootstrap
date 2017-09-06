package com.becheer.donation.model;


import com.becheer.donation.model.base.BaseModel;

import java.sql.Date;

public class Project extends BaseModel {
    private long id;

    private String projectNo;

    private String projectName;

    private long projectTypeId;

    private String summary;

    private String content;

    private long projectTargetAmout;

    private String thumbImg;

    private int enable;

    private Date startTime;

    private Date endTime;

    private int longTerm;

    private String recordStatus;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        id = id;
    }

    public String getProjectNo() {
        return projectNo;
    }

    public void setProjectNo(String projectNo) {
        this.projectNo = projectNo;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public long getProjectTypeId() {
        return projectTypeId;
    }

    public void setProjectTypeId(long projectTypeId) {
        this.projectTypeId = projectTypeId;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        summary = summary;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        content = content;
    }

    public long getProjectTargetAmout() {
        return projectTargetAmout;
    }

    public void setProjectTargetAmout(long projectTargetAmout) {
        this.projectTargetAmout = projectTargetAmout;
    }

    public String getThumbImg() {
        return thumbImg;
    }

    public void setThumbImg(String thumbImg) {
        this.thumbImg = thumbImg;
    }

    public int getEnable() {
        return enable;
    }

    public void setEnable(int enable) {
        this.enable = enable;
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

    public String getRecordStatus() {
        return recordStatus;
    }

    public void setRecordStatus(String recordStatus) {
        this.recordStatus = recordStatus;
    }
}
