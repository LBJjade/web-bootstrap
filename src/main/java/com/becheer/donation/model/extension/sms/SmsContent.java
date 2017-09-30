package com.becheer.donation.model.extension.sms;

/*
* SmsContent
* Creator : xiaokepu
* Date : 
*/

public class SmsContent {
    private String account;
    private String password;
    private String mobile;
    private String content;
    private String requestId;
    private String extNo;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getExtNo() {
        return extNo;
    }

    public void setExtNo(String extNo) {
        this.extNo = extNo;
    }
}
