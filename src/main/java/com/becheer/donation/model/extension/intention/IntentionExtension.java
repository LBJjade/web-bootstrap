package com.becheer.donation.model.extension.intention;

import com.becheer.donation.utils.DateUtils;
import com.becheer.donation.utils.StringUtil;

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

    private int status;

    private String statusText;

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

    public String getIntentionAmount() {
        return StringUtil.formatMoney(intentionAmount);
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStatusText() {
        switch (status) {
            case 0:
                return "未审批";
            case 1:
                return "已审批";
            case 2:
                return "已驳回";
            case 3:
                return "已完成";
            default:
                return "";
        }
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
