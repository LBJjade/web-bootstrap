package com.becheer.donation.strings;

/*
* Message 提示消息文本
* Creator : xiaokepu
* Date : 2017-09-16
*/

import org.omg.CORBA.PUBLIC_MEMBER;

public class Message {

    /*
     *通用
     */
    public static final String SERVER_ERROR="服务器错误";
    public static final String PARAMETER_ERROR="参数错误";

    /*
     *登录
     */
    public static final String LOGIN_MOBILE_NOT_EXIST="手机号不存在";

    public static final String LOGIN_ACCOUNT_DISABLED="账号已被禁用";

    public static final String LOGIN_PASSWORD_ERROR="密码错误";

    public static final String LOGIN_SUCCESS="登录成功";

    public static final String LOGIN_CODE_NULL="请输入验证码";

    public static final String LOGIN_CODE_ERROE="验证码错误";

    public static final String LOGIN_MOBILE_ERROR="手机号格式错误";

    /*
     *信息公开
     */
    public static final String PUBLICITY_ARTICLE_TYPE_SUCCESS="获取文章类别成功";

    public static final String PUBLICITY_ARTICLE_GET_LIST_SUCCESS="获取文章成功";

    /*
     *板块
     */
    public static final String BLOCK_INDIVIDUAL_BLOCKID="参数错误";

    public static final String BLOCK_GET_ARTICLE_SUCCESS="获取文章成功";

    public static final String BLOCK_GET_PROJECT_SUCCESS="获取项目成功";
}
