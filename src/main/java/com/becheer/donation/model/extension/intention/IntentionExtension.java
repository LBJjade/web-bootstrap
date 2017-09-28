package com.becheer.donation.model.extension.intention;

import java.util.Date;

/*
* IntentionExtension
* Creator : xiaokepu
* Date : 2017-09-28
*/
public class IntentionExtension {
    private long id;

    private String projectName;

    private String projectTypeName;

    private long intentionAmount;

    private Date createTime;

    private int enable;

    private String remark;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectTypeName() {
        return projectTypeName;
    }

    public void setProjectTypeName(String projectTypeName) {
        this.projectTypeName = projectTypeName;
    }

    public long getIntentionAmount() {
        return intentionAmount;
    }

    public void setIntentionAmount(long intentionAmount) {
        this.intentionAmount = intentionAmount;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
