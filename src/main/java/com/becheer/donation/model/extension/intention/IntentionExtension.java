package com.becheer.donation.model.extension.intention;

import com.becheer.donation.utils.DateUtils;

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

    //-1已作废 0已提交 1 审核中 2 确认合同中 3已驳回 4已完成
    private int enable;

    private String status;

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

    public String getCreateTime() {
        return DateUtils.dateFormat(createTime);
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

    public String getStatus() {
        switch (enable) {
            case -1:
                return "已作废";
            case 0:
                return "已提交";
            case 1:
                return "审核中";
            case 2:
                return "确认合同中";
            case 3:
                return "已驳回";
            case 4:
                return "已完成";
            default:
                return "";
        }
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
