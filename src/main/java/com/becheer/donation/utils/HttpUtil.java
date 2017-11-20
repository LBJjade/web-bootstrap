package com.becheer.donation.utils;

import com.alibaba.fastjson.JSONObject;
import com.becheer.donation.model.extension.member.MemberSessionExtension;
import com.becheer.donation.strings.ConstString;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.Normalizer;
import java.util.regex.Pattern;

/*
* HttpUtil HTTP工具
* Creator : xiaokepu
* Date : 2017-09-27
*/

public class HttpUtil {

    //获取当前登录用户
    public static MemberSessionExtension GetCurrentUser(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (null == session) {
            return null;
        }
        JSONObject sessionObject = (JSONObject) session.getAttribute(ConstString.MEMBER_SESSION_CODE);
        if (sessionObject == null) {
            return null;
        }
        MemberSessionExtension result = new MemberSessionExtension();
        result.setMemberId(sessionObject.getIntValue("memberId"));
        result.setMobile(sessionObject.getString("mobile"));
        result.setValidation(sessionObject.getIntValue("validation"));
        result.setMemberName(sessionObject.getString("memberName"));
        result.setRole(sessionObject.getIntValue("role"));
        result.setAvator(sessionObject.getString("avator"));
        return result;
    }

    /**
     * 提取html中的文字
     *
     * @param html
     * @return
     */
    public static String htmlToText(String html) {
        if (StringUtils.isNotBlank(html)) {
            return html.replaceAll("(?s)<[^>]*>(\\s*<[^>]*>)*", " ");
        }
        return "";
    }


    /**
     * 替换HTML脚本
     *
     * @param value
     * @return
     */
    public static String cleanXSS(String value) {
        //You'll need to remove the spaces from the html entities below
        value = value.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
        value = value.replaceAll("\\(", "&#40;").replaceAll("\\)", "&#41;");
        value = value.replaceAll("'", "&#39;");
        value = value.replaceAll("eval\\((.*)\\)", "");
        value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");
        value = value.replaceAll("script", "");
        return value;
    }

    /**
     * 过滤XSS注入
     *
     * @param value
     * @return
     */
    public static String filterXSS(String value) {
        String cleanValue = null;
        if (value != null) {
            cleanValue = Normalizer.normalize(value, Normalizer.Form.NFD);
            // Avoid null characters
            cleanValue = cleanValue.replaceAll("\0", "");

            // Avoid anything between script tags
            Pattern scriptPattern = Pattern.compile("<script>(.*?)</script>", Pattern.CASE_INSENSITIVE);
            cleanValue = scriptPattern.matcher(cleanValue).replaceAll("");

            // Avoid anything in a src='...' type of expression
            scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\'(.*?)\\\'", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            cleanValue = scriptPattern.matcher(cleanValue).replaceAll("");

            scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\"(.*?)\\\"", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            cleanValue = scriptPattern.matcher(cleanValue).replaceAll("");

            // Remove any lonesome </script> tag
            scriptPattern = Pattern.compile("</script>", Pattern.CASE_INSENSITIVE);
            cleanValue = scriptPattern.matcher(cleanValue).replaceAll("");

            // Remove any lonesome <script ...> tag
            scriptPattern = Pattern.compile("<script(.*?)>", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            cleanValue = scriptPattern.matcher(cleanValue).replaceAll("");

            // Avoid eval(...) expressions
            scriptPattern = Pattern.compile("eval\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            cleanValue = scriptPattern.matcher(cleanValue).replaceAll("");

            // Avoid expression(...) expressions
            scriptPattern = Pattern.compile("expression\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            cleanValue = scriptPattern.matcher(cleanValue).replaceAll("");

            // Avoid javascript:... expressions
            scriptPattern = Pattern.compile("javascript:", Pattern.CASE_INSENSITIVE);
            cleanValue = scriptPattern.matcher(cleanValue).replaceAll("");

            // Avoid vbscript:... expressions
            scriptPattern = Pattern.compile("vbscript:", Pattern.CASE_INSENSITIVE);
            cleanValue = scriptPattern.matcher(cleanValue).replaceAll("");

            // Avoid onload= expressions
            scriptPattern = Pattern.compile("onload(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            cleanValue = scriptPattern.matcher(cleanValue).replaceAll("");
        }
        return cleanValue;
    }

    /**
     * 发送请求
     *
     * @param urlStr   请求地址
     * @param content  内容
     * @param encoding 编码
     * @param type     请求方式 GET/POST
     * @param timeOut  连接超时时间
     * @return
     */
    public static String sendRequest(String urlStr, String content, String encoding, String type, int timeOut) {
        URL url = null;
        HttpURLConnection connection = null;
        try {
            url = new URL(urlStr);
            // 新建连接实例
            connection = (HttpURLConnection) url.openConnection();
            //设置连接超时时间
            connection.setConnectTimeout(timeOut);
            //打开输出流
            connection.setDoOutput(true);// 是否打开输出流 true|false
            //打开输入流
            connection.setDoInput(true);// 是否打开输入流true|false
            //提交方式
            connection.setRequestMethod(type);// 提交方法POST|GET
            //不缓存
            connection.setUseCaches(false);// 是否缓存true|false
            //打开连接
            connection.connect();
            DataOutputStream out = new DataOutputStream(connection.getOutputStream());
            out.writeBytes(content);
            out.flush();
            // 关闭输出流
            out.close();
            //读取服务端返回的数据
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    connection.getInputStream(), encoding));
            StringBuffer buffer = new StringBuffer();
            String line = "";
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            reader.close();
            return buffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                // 关闭连接
                connection.disconnect();
            }
        }
        return null;
    }
}
