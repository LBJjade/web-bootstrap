package com.becheer.core.support.pay;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.becheer.core.util.HttpUtil;

import java.util.HashMap;
import java.util.Map;

public class WxPay {
	static Logger log = LogManager.getLogger(WxPay.class);
	// 统一下单接口
	private static final String UNIFIEDORDER_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
	// 订单查询
	private static final String ORDERQUERY_URL = "https://api.mch.weixin.qq.com/pay/orderquery";
	// 关闭订单
	private static final String CLOSEORDER_URL = "https://api.mch.weixin.qq.com/pay/closeorder";
	// 撤销订单
	private static final String REVERSE_URL = "https://api.mch.weixin.qq.com/secapi/pay/reverse";
	// 申请退款
	private static final String REFUND_URL = "https://api.mch.weixin.qq.com/secapi/pay/refund";
	// 查询退款
	private static final String REFUNDQUERY_URL = "https://api.mch.weixin.qq.com/pay/refundquery";
	// 下载对账单
	private static final String DOWNLOADBILLY_URL = "https://api.mch.weixin.qq.com/pay/downloadbill";
	// 交易保障
	private static final String REPORT_URL = "https://api.mch.weixin.qq.com/payitil/report";
	// 转换短链接
	private static final String SHORT_URL = "https://api.mch.weixin.qq.com/tools/shorturl";
	// 授权码查询openId接口
	private static final String AUTHCODETOOPENID_URL = "https://api.mch.weixin.qq.com/tools/authcodetoopenid";
	// 刷卡支付
	private static final String MICROPAY_URL = "https://api.mch.weixin.qq.com/pay/micropay";
	// 企业付款
	private static final String TRANSFERS_URL = "https://api.mch.weixin.qq.com/mmpaymkttransfers/promotion/transfers";
	// 查询企业付款
	private static final String GETTRANSFERINFO_URL = "https://api.mch.weixin.qq.com/mmpaymkttransfers/gettransferinfo";

	/// SANDBOX
	// 统一下单接口
	private static final String SANDBOX_UNIFIEDORDER_URL = "https://api.mch.weixin.qq.com/sandbox/pay/unifiedorder";
	// 订单查询
	private static final String SANDBOX_ORDERQUERY_URL = "https://api.mch.weixin.qq.com/sandbox/pay/orderquery";
	// 关闭订单
	private static final String SANDBOX_CLOSEORDER_URL = "https://api.mch.weixin.qq.com/sandbox/pay/closeorder";
	// 撤销订单
	private static final String SANDBOX_REVERSE_URL = "https://api.mch.weixin.qq.com/sandbox/secapi/pay/reverse";
	// 申请退款
	private static final String SANDBOX_REFUND_URL = "https://api.mch.weixin.qq.com/sandbox/secapi/pay/refund";
	// 查询退款
	private static final String SANDBOX_REFUNDQUERY_URL = "https://api.mch.weixin.qq.com/sandbox/pay/refundquery";
	// 下载对账单
	private static final String SANDBOX_DOWNLOADBILLY_URL = "https://api.mch.weixin.qq.com/sandbox/pay/downloadbill";
	// 交易保障
	private static final String SANDBOX_REPORT_URL = "https://api.mch.weixin.qq.com/sandbox/payitil/report";
	// 转换短链接
	private static final String SANDBOX_SHORT_URL = "https://api.mch.weixin.qq.com/sandbox/tools/shorturl";
	// 授权码查询openId接口
	private static final String SANDBOX_AUTHCODETOOPENID_URL = "https://api.mch.weixin.qq.com/sandbox/tools/authcodetoopenid";
	// 刷卡支付
	private static final String SANDBOX_MICROPAY_URL = "https://api.mch.weixin.qq.com/sandbox/pay/micropay";
	// 企业付款
	private static final String SANDBOX_TRANSFERS_URL = "https://api.mch.weixin.qq.com/sandbox/mmpaymkttransfers/promotion/transfers";
	// 查询企业付款
	private static final String SANDBOX_GETTRANSFERINFO_URL = "https://api.mch.weixin.qq.com/sandbox/mmpaymkttransfers/gettransferinfo";

	private WxPay() {
	}

	/**
	 * 交易类型枚举
	 * 
	 * @author Javen 2017年4月15日
	 *         JSAPI--公众号支付、NATIVE--原生扫码支付、APP--app支付，统一下单接口trade_type的传参可参考这里
	 *         MICROPAY--刷卡支付，刷卡支付有单独的支付接口，不调用统一下单接口
	 */
	public static enum TradeType {
		JSAPI, NATIVE, APP, WAP, MICROPAY
	}

	public static Map<String, String> unifiedOrder(String outTradeNo, String productId, String totalFee) {
		String body = "广东省世纪爱心慈善基金会";
		String detail = "";
		String attach = "";
		String appId = "wxc82ba7dbbe892628";
		String appSecret = "2E26416f4392b6fa8755d08a41150b01";
		String mchId = "1448460302";
		String spBillCreateIP = "116.23.155.174";
		String notifyURL= "http://donation.becheer.com";
		String tradeType = "NATIVE";
		String deviceInfo = "WEB";
		String subAppId = "";
		String subMchId = "";

//		Map<String, String> map = new HashMap<>();
//		map.put("out_trade_no", outTradeNo);
//		map.put("product_id", productId);
//		map.put("total_fee", totalFee);
//
//		map.put("body", body);
//		map.put("appid", appId);
//		map.put("mch_id", mchId);
//		map.put("spbill_create_ip", spBillCreateIP);
//		map.put("notify_url", notifyURL);
//		map.put("trade_type", tradeType);
//		map.put("device_info", deviceInfo);


		Map<String, String> map = WxPayHelper.buildUnifiedOrderParasMap(appId, subAppId, mchId, subMchId, deviceInfo, body, detail, attach,
				outTradeNo, totalFee, spBillCreateIP, notifyURL, tradeType, appSecret, productId);

		String prepayXML = unifiedOrder(map);
		return WxPayHelper.xmlToMap(prepayXML);
	}

	/**
	 * 统一下单
	 * 服务商模式接入文档:https://pay.weixin.qq.com/wiki/doc/api/native_sl.php?chapter=9_1
	 * 商户模式接入文档:https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=9_1
	 * 
	 * @param params
	 * @return
	 */
	public static String unifiedOrder(Map<String, String> params) {
		return doPost(UNIFIEDORDER_URL, params);
	}

	/**
	 * 订单查询
	 * 服务商模式接入文档:https://pay.weixin.qq.com/wiki/doc/api/micropay_sl.php?chapter=9_2
	 * 商户模式接入文档:https://pay.weixin.qq.com/wiki/doc/api/micropay.php?chapter=9_2
	 * 
	 * @param params
	 *            请求参数
	 * @return
	 */
	public static String orderQuery(Map<String, String> params) {
		return doPost(ORDERQUERY_URL, params);
	}

	/**
	 * 关闭订单
	 * 服务商模式接入文档:https://pay.weixin.qq.com/wiki/doc/api/jsapi_sl.php?chapter=9_3
	 * 商户模式接入文档:https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_3
	 * 
	 * @param params
	 * @return
	 */
	public static String closeOrder(Map<String, String> params) {
		return doPost(CLOSEORDER_URL, params);
	}

	/**
	 * 撤销订单
	 * 服务商模式接入文档:https://pay.weixin.qq.com/wiki/doc/api/micropay_sl.php?chapter=9_11&index=3
	 * 商户模式接入文档:https://pay.weixin.qq.com/wiki/doc/api/micropay.php?chapter=9_11&index=3
	 * 
	 * @param params
	 *            请求参数
	 * @param certPath
	 *            证书文件目录
	 * @param certPass
	 *            证书密码
	 * @return
	 */
	public static String orderReverse(Map<String, String> params, String certPath, String certPass) {
		return doPostSSL(REVERSE_URL, params, certPath, certPass);
	}

	/**
	 * 申请退款
	 * 服务商模式接入文档:https://pay.weixin.qq.com/wiki/doc/api/micropay_sl.php?chapter=9_4
	 * 商户模式接入文档:https://pay.weixin.qq.com/wiki/doc/api/micropay.php?chapter=9_4
	 * 
	 * @param params
	 *            请求参数
	 * @param certPath
	 *            证书文件目录
	 * @param certPass
	 *            证书密码
	 * @return
	 */
	public static String orderRefund(Map<String, String> params, String certPath, String certPass) {
		return doPostSSL(REFUND_URL, params, certPath, certPass);
	}

	/**
	 * 查询退款
	 * 服务商模式接入文档:https://pay.weixin.qq.com/wiki/doc/api/micropay_sl.php?chapter=9_5
	 * 商户模式接入文档:https://pay.weixin.qq.com/wiki/doc/api/micropay.php?chapter=9_5
	 * 
	 * @param params
	 *            请求参数
	 * @return
	 */
	public static String orderRefundQuery(Map<String, String> params) {
		return doPost(REFUNDQUERY_URL, params);
	}

	/**
	 * 下载对账单
	 * 服务商模式接入文档:https://pay.weixin.qq.com/wiki/doc/api/micropay_sl.php?chapter=9_6
	 * 商户模式接入文档:https://pay.weixin.qq.com/wiki/doc/api/micropay.php?chapter=9_6
	 * 
	 * @param params
	 *            请求参数
	 * @return
	 */
	public static String downloadBill(Map<String, String> params) {
		return doPost(DOWNLOADBILLY_URL, params);
	}

	/**
	 * 交易保障
	 * 服务商模式接入文档:https://pay.weixin.qq.com/wiki/doc/api/micropay_sl.php?chapter=9_14&index=7
	 * 商户模式接入文档:https://pay.weixin.qq.com/wiki/doc/api/micropay.php?chapter=9_14&index=7
	 * 
	 * @param params
	 *            请求参数
	 * @return
	 */
	public static String orderReport(Map<String, String> params) {
		return doPost(REPORT_URL, params);
	}

	/**
	 * 转换短链接
	 * 服务商模式接入文档:https://pay.weixin.qq.com/wiki/doc/api/micropay_sl.php?chapter=9_9&index=8
	 * 商户模式接入文档:https://pay.weixin.qq.com/wiki/doc/api/micropay.php?chapter=9_9&index=8
	 * 
	 * @param params
	 *            请求参数
	 * @return
	 */
	public static String toShortUrl(Map<String, String> params) {
		return doPost(SHORT_URL, params);
	}

	/**
	 * 授权码查询openId
	 * 服务商模式接入文档:https://pay.weixin.qq.com/wiki/doc/api/micropay_sl.php?chapter=9_12&index=9
	 * 商户模式接入文档:
	 * https://pay.weixin.qq.com/wiki/doc/api/micropay.php?chapter=9_13&index=9
	 * 
	 * @param params
	 *            请求参数
	 * @return
	 */
	public static String authCodeToOpenid(Map<String, String> params) {
		return doPost(AUTHCODETOOPENID_URL, params);
	}

	/**
	 * 刷卡支付
	 * 服务商模式接入文档:https://pay.weixin.qq.com/wiki/doc/api/micropay_sl.php?chapter=9_10&index=1
	 * 商户模式接入文档:
	 * https://pay.weixin.qq.com/wiki/doc/api/micropay.php?chapter=9_10&index=1
	 *
	 * @param params
	 *            请求参数
	 * @return
	 */
	public static String micropay(Map<String, String> params) {
		return WxPay.doPost(MICROPAY_URL, params);
	}

	/**
	 * 企业付款
	 * 
	 * @param params
	 *            请求参数
	 * @param certPath
	 *            证书文件目录
	 * @param certPassword
	 *            证书密码
	 * @return {String}
	 */
	public static String transfers(Map<String, String> params, String certPath, String certPassword) {
		return WxPay.doPostSSL(TRANSFERS_URL, params, certPath, certPassword);
	}

	/**
	 * 查询企业付款
	 * 
	 * @param params
	 *            请求参数
	 * @param certPath
	 *            证书文件目录
	 * @param certPassword
	 *            证书密码
	 * @return {String}
	 */
	public static String getTransferInfo(Map<String, String> params, String certPath, String certPassword) {
		return WxPay.doPostSSL(GETTRANSFERINFO_URL, params, certPath, certPassword);
	}

	/**
	 * 商户模式下 扫码模式一之生成二维码
	 * 
	 * @param appid
	 * @param mch_id
	 * @param product_id
	 * @param partnerKey
	 * @param isToShortUrl
	 *            是否转化为短连接
	 * @return
	 */
	public static String getCodeUrl(String appid, String mch_id, String product_id, String partnerKey,
			boolean isToShortUrl) {
		String url = "weixin://wxpay/bizpayurl?sign=XXXXX&appid=XXXXX&mch_id=XXXXX&product_id=XXXXX&time_stamp=XXXXX&nonce_str=XXXXX";
		String timeStamp = Long.toString(System.currentTimeMillis() / 1000);
		String nonceStr = Long.toString(System.currentTimeMillis());
		Map<String, String> packageParams = new HashMap<String, String>();
		packageParams.put("appid", appid);
		packageParams.put("mch_id", mch_id);
		packageParams.put("product_id", product_id);
		packageParams.put("time_stamp", timeStamp);
		packageParams.put("nonce_str", nonceStr);
		String packageSign = WxPayHelper.createSign(packageParams, partnerKey);
		String qrCodeUrl = WxPayHelper.replace(url, "XXXXX", packageSign, appid, mch_id, product_id, timeStamp,
				nonceStr);
		if (isToShortUrl) {
			String shortResult = WxPay
					.toShortUrl(WxPayHelper.buildShortUrlParasMap(appid, null, mch_id, null, qrCodeUrl, partnerKey));
			if (log.isDebugEnabled()) {
				log.info(shortResult);
			}
			Map<String, String> shortMap = WxPayHelper.xmlToMap(shortResult);
			String return_code = shortMap.get("return_code");
			if (WxPayHelper.codeIsOK(return_code)) {
				String result_code = shortMap.get("result_code");
				if (WxPayHelper.codeIsOK(result_code)) {
					qrCodeUrl = shortMap.get("short_url");
				}
			}
		}

		return qrCodeUrl;
	}

	public static String doPost(String url, Map<String, String> params) {
		String param = WxPayHelper.toXml(params);
		return HttpUtil.post(url, param);
	}

	public static String doPostSSL(String url, Map<String, String> params, String certPath, String certPass) {
		return HttpUtil.postSSL(url, WxPayHelper.toXml(params), certPath, certPass);
	}
}
