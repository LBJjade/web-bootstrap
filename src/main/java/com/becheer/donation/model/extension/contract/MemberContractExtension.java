package com.becheer.donation.model.extension.contract;

/*
* MemberContractExtension 会员合同列表实体
* Creator : xiaokepu
* Date : 2017-10-08
*/

import com.becheer.donation.utils.StringUtil;

import java.util.Date;

public class MemberContractExtension {
    private long id;

    private String contractName;

    private Date signTime;

    private long contractAmount;

    private int enable;

    private int status;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public Date getSignTime() {
        return signTime;
    }

    public void setSignTime(Date signTime) {
        this.signTime = signTime;
    }

    public String getContractAmount() {
        return StringUtil.formatMoney(contractAmount);
    }

    public void setContractAmount(long contractAmount) {
        this.contractAmount = contractAmount;
    }

    public int getEnable() {
        return enable;
    }

    public void setEnable(int enable) {
        this.enable = enable;
    }

    public String getStatus(){
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

    public void setStatus(int status) {
        this.status = status;
    }
}
