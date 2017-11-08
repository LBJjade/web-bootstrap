package com.becheer.donation.model.extension.project;

import com.becheer.donation.configs.FileConfig;
import com.becheer.donation.service.SpringContextUtil;
import com.becheer.donation.strings.Message;
import com.becheer.donation.utils.DateUtils;
import com.becheer.donation.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/*
* MemberProjectDetailExtension
* Creator : xiaokepu
* Date : 2017-10-10
*/
public class MemberProjectDetailExtension {

    private long id;

    private long projectId;

    private String projectName;

    private String content;

    private String contractName;

    private long contractAmount;

    private long donatedAmount;

    private long acceptedAmount;

    private long allocateAmount;

    private Date beginTime;

    private Date endTime;

    private int status;

    private String statusText;

    private Date signTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getContent() {
        return content.replaceAll(Message.REPLACE_HOLDER_PROJECT_CONTENT,((FileConfig) SpringContextUtil.getBean("fileConfig")).getFileRoot());
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public String getContractAmount() {
        return StringUtil.formatMoney(contractAmount);
    }

    public void setContractAmount(long contractAmount) {
        this.contractAmount = contractAmount;
    }

    public String getDonatedAmount() {
        return StringUtil.formatMoney(donatedAmount);
    }

    public void setDonatedAmount(long donatedAmount) {
        this.donatedAmount = donatedAmount;
    }

    public long getAcceptedAmount() {
        return acceptedAmount;
    }

    public void setAcceptedAmount(long acceptedAmount) {
        this.acceptedAmount = acceptedAmount;
    }

    public long getAllocateAmount() {
        return allocateAmount;
    }

    public void setAllocateAmount(long allocateAmount) {
        this.allocateAmount = allocateAmount;
    }

    public String getBeginTime() {
        return DateUtils.dateFormat(beginTime,"yyyy-MM-dd");
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return DateUtils.dateFormat(endTime,"yyyy-MM-dd");
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getSignTime() {
        return signTime;
    }

    public void setSignTime(Date signTime) {
        this.signTime = signTime;
    }

    public String getStatusText(){
        switch (status){
            case 0:
                return "编辑中";
            case 1:
                return "审批中";
            case 2:
                return "已审批";
            case 3:
                return "已驳回";
            case 4:
                return "重新编辑中";
            case 5:
                //此处临时处理，因枚举值缺失,须同后台沟通统一。
                if (signTime!=null){
                    return "捐赠人已签订";
                }else {
                    return "待签订";
                }
            case 6:
                return "基金会已签订";
            case 7:
                return "执行中";
            case 8:
                return "已完成";
            case 9:
                return "已终止";
            case 10:
                return "已作废";
            default:
                return "";
        }
    }
}
