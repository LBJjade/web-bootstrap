package com.becheer.donation.model.extension.project;

/*
* ProjectDetailExtension 项目明细页面实体
* Creator : xiaokepu
* Date : 2017-09-25
*/

import com.becheer.donation.configs.FileConfig;
import com.becheer.donation.service.SpringContextUtil;
import com.becheer.donation.strings.Message;
import com.becheer.donation.utils.DateUtils;
import com.becheer.donation.utils.StringUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class ProjectDetailExtension {

    private long id;

    private long projectTypeId;

    private String projectName;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date startTime;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date endTime;

    private int longTerm;

    private String content;

    private long projectTargetAmount;

    private long acceptedAmount;

    private String thumbImg;

    private String projectTypeName;

    public String getProjectTypeName() {
        return projectTypeName;
    }

    public void setProjectTypeName(String projectTypeName) {
        this.projectTypeName = projectTypeName;
    }

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

    public String getStartTime() {
        return DateUtils.dateFormat(startTime,"yyyy-MM-dd");
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return DateUtils.dateFormat( endTime,"yyyy-MM-dd");
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

    public String getProjectTargetAmount(){
        return StringUtil.formatMoney(projectTargetAmount);
    }

    public void setProjectTargetAmount(long projectTargetAmount) {
        this.projectTargetAmount = projectTargetAmount;
    }

    public String getContent() {
        return content.replaceAll(Message.REPLACE_HOLDER_PROJECT_CONTENT,((FileConfig) SpringContextUtil.getBean("fileConfig")).getFileRoot());
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAcceptedAmount() {
        return StringUtil.formatMoney(acceptedAmount);
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
