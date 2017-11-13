package com.becheer.donation.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.becheer.donation.model.AliIpDetail;

import javax.servlet.http.HttpServletRequest;

/**
 * ip工具类
 */
public class IPUtil {

    /**
     * 获取IP所在的地址
     *
     * @param ip
     * @return
     */
    public static AliIpDetail getIpLocation(String ip) {
        try {
            //调用淘宝IP接口获取ip地址
            String url = "http://ip.taobao.com/service/getIpInfo.php";
            String response = HttpUtil.sendRequest(url, ip, "utf-8", "POST", 3000);
            if (StringUtil.isNull(response)) {
                return null;
            }
            AliIpDetail result = JSON.parseObject(JSONObject.parseObject(response).get("data").toString(), new TypeReference<AliIpDetail>() {
            });
            return result;
        } catch (Exception ex) {
            return null;
        }
    }


    /**
     * 获取请求的IP
     *
     * @param request
     * @return
     */
    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
