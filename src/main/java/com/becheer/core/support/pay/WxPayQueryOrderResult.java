package com.becheer.core.support.pay;

import java.io.Serializable;

/**
 * 微信支付结果
 */
public class WxPayQueryOrderResult implements Serializable {

    private static final long serialVersionUID = 1L;

    private String returnCode;          //返回状态码 SUCCESS/FAIL 此字段是通信标识，非交易标识，交易是否成功需要查看resultCode来判断
    private String returnMsg;           //返回信息，如非空，为错误原因  签名失败  参数格式校验错误
    private String appid;               //微信分配的公众账号ID（企业号corpid即为此appId）
    private String mchId;               //微信支付分配的商户号
    private String deviceInfo;          //微信支付分配的终端设备号，
    private String nonceStr;            //随机字符串，不长于32位
    private String sign;			    //签名
    private String resultCode;		    //业务结果 SUCCESS/FAIL
    private String errCode;			    //错误返回的信息描述
    private String errCodeDes;		    //错误返回的信息描述
    private String openid;			    //用户在商户appid下的唯一标识
    private String isSubscribe;		    //用户是否关注公众账号，Y-关注，N-未关注，仅在公众账号类型支付有效
    private String tradeType;		    //JSAPI、NATIVE、APP
    private String bankType;		    //银行类型，采用字符串类型的银行标识
    private int totalFee;			    //订单总金额，单位为分
    private String settlementTotalFee;  //应结订单金额=订单金额-非充值代金券金额，应结订单金额<=订单金额。
    private String feeType;			    //货币类型，符合ISO4217标准的三位字母代码，默认人民币：CNY
    private int cashFee;			    //现金支付金额订单现金支付金额
    private String cashFeeType;			//货币类型，符合ISO4217标准的三位字母代码，默认人民币：CNY
    private int couponFee;			    //代金券金额<=订单金额，订单金额-代金券金额=现金支付金额
    private String couponCount;			//代金券使用数量
    private String transactionId;		//微信支付订单号
    private String outTradeNo;			//商户系统的订单号，与请求一致
    private String attach;			    //商家数据包，原样返回
    private String timeEnd;			    //支付完成时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getReturnMsg() {
        return returnMsg;
    }

    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(String deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrCodeDes() {
        return errCodeDes;
    }

    public void setErrCodeDes(String errCodeDes) {
        this.errCodeDes = errCodeDes;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getIsSubscribe() {
        return isSubscribe;
    }

    public void setIsSubscribe(String isSubscribe) {
        this.isSubscribe = isSubscribe;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public String getBankType() {
        return bankType;
    }

    public void setBankType(String bankType) {
        this.bankType = bankType;
    }

    public int getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(int totalFee) {
        this.totalFee = totalFee;
    }

    public String getSettlementTotalFee() {
        return settlementTotalFee;
    }

    public void setSettlementTotalFee(String settlementTotalFee) {
        this.settlementTotalFee = settlementTotalFee;
    }

    public String getFeeType() {
        return feeType;
    }

    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }

    public int getCashFee() {
        return cashFee;
    }

    public void setCashFee(int cashFee) {
        this.cashFee = cashFee;
    }

    public String getCashFeeType() {
        return cashFeeType;
    }

    public void setCashFeeType(String cashFeeType) {
        this.cashFeeType = cashFeeType;
    }

    public int getCouponFee() {
        return couponFee;
    }

    public void setCouponFee(int couponFee) {
        this.couponFee = couponFee;
    }

    public String getCouponCount() {
        return couponCount;
    }

    public void setCouponCount(String couponCount) {
        this.couponCount = couponCount;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }
}
