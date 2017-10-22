package com.becheer.core.support.pay;

import java.io.Serializable;

/**
 * 微信支付结果
 */
public class WxPayReturnToWeixin implements Serializable {

    private static final long serialVersionUID = 1L;

    private String returnCode;          //返回状态码 SUCCESS/FAIL 此字段是通信标识，非交易标识，交易是否成功需要查看resultCode来判断
    private String returnMsg;           //返回信息，如非空，为错误原因  签名失败  参数格式校验错误

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

    public String toXML() {
        StringBuilder xml = new StringBuilder();
        xml.append("<xml>");
        xml.append("  <return_code><![CDATA[");
        xml.append(getReturnCode());
        xml.append(")]]></return_code>");
        xml.append("  <return_msg><![CDATA[");
        xml.append(getReturnMsg());
        xml.append(")]]></return_msg>");
        xml.append("</xml>");
        return xml.toString();
    }
}
