package com.becheer.donation.model.extension.sms;

/*
* SmsResponse
* Creator : xiaokepu
* Date : 2017-09-30
*/
public class SmsResponse {
    private String requestId;

    private String status;

    private String batchId;

    private String errorCode;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
