package com.becheer.donation.strings;

/*
* ConstString
* Creator : xiaokepu
* Date : 2017-09-15
*/

public class ConstString {

    //注册SessionKey
    public static final String REGISTER_SMS_SESSION = "RegisterSession";

    //登录二维码SessionKey
    public static final String LOGIN_VERIFY_CODE = "LoginVerifyCode";

    //登录Session
    public static final String LOGIN_SESSION_NAME = "sjax_member_session";

    //登录Cookie
    public static final String LOGIN_COOKIE_NAME = "sjax_member_cookie";

    //后台Redis键前缀
    public static final String REDIS_BACKEDN_KEY = "iBase4J";

    /**
     * 表名，Redis用
     */
    //会员表
    public static final String TABLE_MEMBER = "dntMember";

    //合同表
    public static final String TABLE_CONTRACT = "dntContract";

    //合同项目表
    public static final String TABLE_CONTRACT_PROJECT = "dntContractProject";

    //合同项目捐赠人表
    public static final String TABLE_CONTRACT_PROJECT_ACCEPTOR = "dntContractProjectAcceptor";

    //申诉
    public static final String TABLE_APPEAL = "dntAppeal";

    //付款记录
    public static final String TABLE_PAYMENT_PLAN = "dntPaymentPlan";
}
