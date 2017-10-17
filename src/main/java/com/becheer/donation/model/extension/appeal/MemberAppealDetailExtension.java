package com.becheer.donation.model.extension.appeal;

/*
* MemberAppealDetailExtension
* Creator : xiaokepu
* Date : 2017-10-14
*/

import com.becheer.donation.utils.DateUtils;

import java.util.Date;

public class MemberAppealDetailExtension {
    private long id;

    private String title;

    private String content;

    private String method;

    private String contractName;

    private String projectName;

    private Date createTime;

    private int status;

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

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getCreateTime() {
        return DateUtils.dateFormat(createTime);
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getStatus() {
        switch(status){
            case 0:
                return "待审核";
            case 1:
                return "处理中";
            case 2:
                return "已处理";
            case 3:
                return "已驳回";
                default:
                    return "";
        }
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
