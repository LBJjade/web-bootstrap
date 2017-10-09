package com.becheer.donation.model.extension.contract;

/*
* MemberContractExtension 会员合同列表实体
* Creator : xiaokepu
* Date : 2017-10-08
*/

import java.util.Date;

public class MemberContractExtension {
    private long id;

    private String contractName;

    private Date signTime;

    private long contractAmount;

    private int enable;

    private String status;

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

    public long getContractAmount() {
        return contractAmount;
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

    public String getStatus() {
        switch (this.enable){
            case 0:
                return "已作废";
            case 1:
                return "正在进行";
            case 2:
                return "已终止";
            case 3:
                return "已完成";
            default: return "";

        }
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
