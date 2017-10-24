package com.becheer.donation.model.extension.wxpay;

/**
 * 预支付下单实体类
 *
 */
public class WxPayPrepayExtension {

    /**
     * 公众账号ID
     */
    private String appId;
    /**
     * 商户号
     */
    private String mchId;
    /**
     * 设备号
     */
    private String deviceInfo;
    /**
     * 随机字符串
     */
    private String nonceStr;
    /**
     * 签名
     */
    private String sign;
    /**
     * 商品描述
     */
    private String body;
    /**
     * 商品详情
     */
    private String detail;
    /**
     * 附加数据
     */
    private String attach;
    /**
     * 商户订单号
     */
    private String outTradeNo;
    /**
     * 货币类型
     */
    private String feeType;
    /**
     * 总金额(分)
     */
    private String totalFee;
    /**
     * 终端IP
     */
    private String spbillCreateIp;
    /**
     * 交易起始时间
     */
    private String timeStart;
    /**
     * 交易结束时间
     */
    private String timeExpire;
    /**
     * 商品标记
     */
    private String goodsTag;
    /**
     * 通知地址
     */
    private String notifyUrl;
    /**
     * 交易类型
     */
    private String tradeType;
    /**
     * 商品ID
     */
    private String productId;
    /**
     * 指定支付方式
     */
    private String limitPay;
    /**
     * 用户标识
     */
    private String openId;

    public String getAppId() {
        return appId;
    }

    public WxPayPrepayExtension setAppId(String appId) {
        this.appId = appId;
        return this;
    }

    public String getMchId() {
        return mchId;
    }

    public WxPayPrepayExtension setMchId(String mchId) {
        this.mchId = mchId;
        return this;
    }

    public String getDeviceInfo() {
        return deviceInfo;
    }

    public WxPayPrepayExtension setDeviceInfo(String deviceInfo) {
        this.deviceInfo = deviceInfo;
        return this;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public WxPayPrepayExtension setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
        return this;
    }

    public String getSign() {
        return sign;
    }

    public WxPayPrepayExtension setSign(String sign) {
        this.sign = sign;
        return this;
    }

    public String getBody() {
        return body;
    }

    public WxPayPrepayExtension setBody(String body) {
        this.body = body;
        return this;
    }

    public String getDetail() {
        return detail;
    }

    public WxPayPrepayExtension setDetail(String detail) {
        this.detail = detail;
        return this;
    }

    public String getAttach() {
        return attach;
    }

    public WxPayPrepayExtension setAttach(String attach) {
        this.attach = attach;
        return this;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public WxPayPrepayExtension setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
        return this;
    }

    public String getFeeType() {
        return feeType;
    }

    public WxPayPrepayExtension setFeeType(String feeType) {
        this.feeType = feeType;
        return this;
    }

    public String getTotalFee() {
        return totalFee;
    }

    public WxPayPrepayExtension setTotalFee(String totalFee) {
        this.totalFee = totalFee;
        return this;
    }

    public String getSpbillCreateIp() {
        return spbillCreateIp;
    }

    public WxPayPrepayExtension setSpbillCreateIp(String spbillCreateIp) {
        this.spbillCreateIp = spbillCreateIp;
        return this;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public WxPayPrepayExtension setTimeStart(String timeStart) {
        this.timeStart = timeStart;
        return this;
    }

    public String getTimeExpire() {
        return timeExpire;
    }

    public WxPayPrepayExtension setTimeExpire(String timeExpire) {
        this.timeExpire = timeExpire;
        return this;
    }

    public String getGoodsTag() {
        return goodsTag;
    }

    public WxPayPrepayExtension setGoodsTag(String goodsTag) {
        this.goodsTag = goodsTag;
        return this;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public WxPayPrepayExtension setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
        return this;
    }

    public String getTradeType() {
        return tradeType;
    }

    public WxPayPrepayExtension setTradeType(String tradeType) {
        this.tradeType = tradeType;
        return this;
    }

    public String getProductId() {
        return productId;
    }

    public WxPayPrepayExtension setProductId(String productId) {
        this.productId = productId;
        return this;
    }

    public String getLimitPay() {
        return limitPay;
    }

    public WxPayPrepayExtension setLimitPay(String limitPay) {
        this.limitPay = limitPay;
        return this;
    }

    public String getOpenId() {
        return openId;
    }

    public WxPayPrepayExtension setOpenId(String openId) {
        this.openId = openId;
        return this;
    }
}
