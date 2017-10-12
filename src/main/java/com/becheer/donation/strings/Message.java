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

    /**
     * 注册
     */
    public static final String REGISTER_SMS_SEND_ERROR="短信发送失败，请重试";
    public static final String REGISTER_SMS_SEND_SUCCESS="短信发送成功";
    public static final String REGISTER_SMS_INDIVIDUAL_TEMPLATE="短信模板错误";
    public static final String REGISTER_SMS_USE_OUT="您本天的短信次数已用尽";


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

    /*
     *无合同捐赠
     */
    public static final String NOCONTRACT_GET_RECENT_SUCCESS="获取爱心动态成功";

    /*
     *项目分类
     */
    public static final String PROJECT_TYPE_GET_SUCCESS="获取项目类别成功";

    /**
     * 项目
     */
    public static final String PROJECT_GET_LIST_SUCCESS="获取项目列表成功";

    public static final String PROJECT_PROGRESS_GET_SUCCESS="获取项目进展成功";

    /**
     * 个人信息页面
     */

    public static final String MEMBER_GET_SUCCESS="获取会员成功";

    public static final String MEMBER_NOT_EXISTS="会员不存在";

    public static final String MEMBER_DISABLED="会员已禁用";

    public static final String MEMBER_ROLE_ERROR="未知的角色";


    /**
     * 我的消息页面
     */
    public static final String MESSAGE_GET_SUCCESS="获取会员成功";

    /**
     * 捐赠意向
     */
    public static final String INTENTION_GET_SUCCESS="获取捐赠意向成功";

    /**
     * 合同
     */
    public static final String MEMBER_GET_CONTRACT_SUCCESS="获取合同成功";

    /**
     * 捐赠计划
     */
    public static final String MEMBER_GET_PAYMENT_PLAN_SUCCESS="获取捐赠计划成功";


    /**
     * 项目
     */
    public static final String MEMBER_PROJECT_GET_PROGRESS_SUCCESS="获取参与记录成功";

    /**
     * 捐赠意向明细页
     */
    public static final String MEMBER_INTENTION_PROGRESS_SUCCESS="获取捐赠申请进度成功";

    public static final String MEMBER_INTENTION_PROGRESS_ADD_SUCCESS="提交成功";

    public static final String MEMBER_INTENTION_PROGRESS_ADD_FAILED="提交失败";

    /**
     * 申诉
     */



}
