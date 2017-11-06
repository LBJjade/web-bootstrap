package com.becheer.donation.model.extension.appeal;

/*
* MemberAppealExtension
* Creator : xiaokepu
* Date : 2017-10-11
*/

import com.becheer.donation.utils.DateUtils;

import java.util.Date;

public class MemberAppealExtension {
    private int id;

    private String appealTitle;

    private String projectName;

    private String contractName;

    private String appealMethod;

    private Date createTime;

    private int status;

    private String statusText;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAppealTitle() {
        return appealTitle;
    }

    public void setAppealTitle(String appealTitle) {
        this.appealTitle = appealTitle;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public String getAppealMethod() {
        return appealMethod;
    }

    public void setAppealMethod(String appealMethod) {
        this.appealMethod = appealMethod;
    }

    public String getCreateTime() {
        return DateUtils.dateFormat(createTime);
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStatusText() {
        switch (status){
            case 0:
                return "已提交";
            case 1:
                return "已驳回";
            case 2:
                return "处理中";
            case 3:
                return "已处理";
                default:
                    return "";
        }
    }
}
