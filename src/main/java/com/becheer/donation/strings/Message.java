package com.becheer.donation.strings;

/*
* Message 提示消息文本
* Creator : xiaokepu
* Date : 2017-09-16
*/

public class Message {

    /*
     *通用
     */
    public static final String SERVER_ERROR = "服务器错误";
    public static final String PARAMETER_ERROR = "参数错误";
    public static final String UNAUTHORIZED = "授权失败";
    public static final String ACTION_SUCCESS = "操作成功";
    public static final String ACTION_FAILED = "操作失败";

    /*
     *验证
     */
    public static final String VALIDATION_MOBILE_FAILED = "手机号格式错误";

    /**
     * 注册
     */
    public static final String REGISTER_REGISTER_SUCCESS = "注册成功";

    public static final String REGISTER_MOBILE_EXIST = "该手机号已被使用";
    public static final String REGISTER_NO_EXIST = "该手机号不存在";
    public static final String REGISTER_SMS_SEND_ERROR = "短信发送失败，请重试";
    public static final String REGISTER_SMS_SEND_SUCCESS = "短信发送成功";
    public static final String REGISTER_SMS_INDIVIDUAL_TEMPLATE = "短信模板错误";
    public static final String REGISTER_SMS_USE_OUT = "您本天的短信次数已用尽";
    public static final String REGISTER_SMS_TIME_OUT = "请不要重复获取短信";


    /*
     *登录
     */
    public static final String LOGIN_MOBILE_NOT_EXIST = "手机号不存在";

    public static final String LOGIN_ACCEPTOR_NOT_EXIST = "账号不存在";

    public static final String LOGIN_ACCOUNT_DISABLED = "账号已被禁用";

    public static final String LOGIN_PASSWORD_ERROR = "密码错误";

    public static final String LOGIN_SUCCESS = "登录成功";

    public static final String LOGIN_CODE_NULL = "请输入验证码";

    public static final String LOGIN_CODE_ERROR = "输入的授号与当前授号不符";

    public static final String LOGIN_CODE_ERROE = "验证码错误";

    public static final String LOGIN_CODE_SUCCESS = "验证码正确";

    public static final String LOGIN_MOBILE_ERROR = "手机号格式错误";

    public static final String LOGINOUT_SUCCESS = "注销成功";

    public static final String LOGIN_COOKIE_FAILED = "登录失败";

    public static final String PASSWORD_CHANG_SUCCESS = "修改密码成功";

    public static final String PASSWORD_CHANG_ERROR = "修改密码失败";

    public static final String MEMBER_UNENABLE = "无效账户,账户已删除";

    public static final String LOGIN_AUTH_NO_NULL = "授号错误";


    /*
     *信息公开
     */
    public static final String PUBLICITY_ARTICLE_TYPE_SUCCESS = "获取文章类别成功";

    public static final String PUBLICITY_ARTICLE_GET_LIST_SUCCESS = "获取文章成功";

    /*
     *板块
     */
    public static final String BLOCK_INDIVIDUAL_BLOCKID = "参数错误";

    public static final String BLOCK_GET_ARTICLE_SUCCESS = "获取文章成功";

    public static final String BLOCK_GET_PROJECT_SUCCESS = "获取项目成功";

    /*
     *无合同捐赠
     */
    public static final String NOCONTRACT_GET_RECENT_SUCCESS = "获取爱心动态成功";

    /*
     *合同捐赠
     */
    public static final String CONTRACT_HAD_DONATE = "你已经捐赠过了";

    /*
     *项目分类
     */
    public static final String PROJECT_TYPE_GET_SUCCESS = "获取项目类别成功";

    /**
     * 项目
     */
    public static final String PROJECT_GET_LIST_SUCCESS = "获取项目列表成功";

    public static final String PROJECT_PROGRESS_GET_SUCCESS = "获取项目进展成功";

    /**
     * 个人信息页面
     */

    public static final String MEMBER_GET_SUCCESS = "获取会员成功";

    public static final String MEMBER_NOT_EXISTS = "会员不存在";

    public static final String MEMBER_DISABLED = "会员已禁用";

    public static final String MEMBER_ROLE_ERROR = "未知的角色";

    public static final String MEMBER_UPDATE_SUCCESS = "保存成功";

    public static final String MEMBER_UPDATE_ERROR = "保存失败";

    public static final String MEMBER_AVATOR_UPLOAD_ERROR = "上传失败";

    public static final String MEMBER_AVATOR_UPLOAD_SUCCESS = "上传成功";

    public static final String MEMBER_AVATOR_NULL = "请先选择头像";

    public static final String MEMBER_IMG_OVERLIMIT = "请选择2M以内的图片";

    public static final String MEMBER_AREA_ERROR = "地址信息错误";

    /**
     * 我的消息页面
     */
    public static final String MESSAGE_GET_SUCCESS = "获取会员成功";

    /**
     * 捐赠意向
     */
    public static final String INTENTION_GET_SUCCESS = "获取捐赠意向成功";
    public static final String INTENTION_ADD_SUCCESS = "提交成功";
    public static final String INTENTION_MEMBER_APPROVEING = "您的资料正在审核中，请在审核通过后提交捐赠申请";
    public static final String INTENTION_MEMBER_NOT_VAILD = "您的账号尚未实名认证，请到会员中心提交实名认证资料";

    /**
     * 合同
     */
    public static final String MEMBER_GET_CONTRACT_SUCCESS = "获取合同成功";
    public static final String MEMBER_CONTRACT_ATTACH_GET_SUCCESS = "获取合同附件成功";

    /**
     * 三方合同
     */
    public static final String ACCEPTER_GET_CONTRACT_SUCCESS = "获取合同成功";

    /**
     * 捐赠计划
     */
    public static final String MEMBER_GET_PAYMENT_PLAN_SUCCESS = "获取捐赠计划成功";


    /**
     * 批资计划
     */
    public static final String ACCEPTER_GET_ALLOCATE_PLAN_SUCCESS = "获取批资计划成功";

    /**
     * 项目
     */
    public static final String MEMBER_PROJECT_GET_PROGRESS_SUCCESS = "获取参与记录成功";

    /**
     * 捐赠意向明细页
     */
    public static final String MEMBER_INTENTION_PROGRESS_SUCCESS = "获取捐赠申请进度成功";

    public static final String MEMBER_INTENTION_PROGRESS_ADD_SUCCESS = "提交成功";

    public static final String MEMBER_INTENTION_PROGRESS_ADD_FAILED = "提交失败";

    public static final String MEMBER_INTENTION_PROGRESS_CONTENT_NULL = "请输入意见内容";

    /**
     * 申诉
     */
    public static final String MEMBER_APPEAL_PROGRESS_SUCCESS = "获取申诉进展成功";

    /**
     * 未读消息数
     */
    public static final String MESSAGE_NUMBER_GET_SUCCESS = "获取消息数成功";
    public static final String MESSAGE_NUMBER_GET_FAILED = "获取消息数失败";
    /**
     * 微信支付
     */
    public static final String WXPAY_SUCCESS = "提交微信支付成功";

    /**
     * 改变状态
     */
    public static final String CHANGE_STATUS_SUCCESS = "状态修改成功";
    public static final String CHANGE_STATUS_FAILED = "状态修改失败";

    /**
     * 得到状态
     */
    public static final String GET_STATUS_SUCCESS = "状态获取成功";
    public static final String GET_STATUS_FAILED = "状态获取失败";

    /**
     * 获取用户消息数
     */
    public static final String GET_MEMBERMESSAGES_SUCCESS = "消息数获取成功";
    public static final String GET_MEMBERMESSAGES_FAILED = "消息数获取失败";

    /**
     * 申诉
     */
    public static final String SUBMIT_APPEAL_SUCCESS = "申诉提交成功";
    public static final String SUBMIT_APPEAL_FAILED = "申诉提交失败";
    public static final String SUBMIT_APPEAL_TITLE_NULL = "请输入申诉主题";
    public static final String SUBMIT_APPEAL_METHOD_NULL = "请选择申诉类型";
    public static final String SUBMIT_APPEAL_CONTENT_NULL = "请输入申诉内容";
    public static final String SUBMIT_APPEAL_ID_NULL = "参数错误";
    public static final String MEMBER_APPEAL_PROGRESS_ADD_SUCCESS = "提交成功";
    public static final String MEMBER_APPEAL_PROGRESS_ADD_FAILED = "提交失败";
    public static final String MEMBER_APPEAL_PROGRESS_CONTENT_NULL = "请输入意见内容";
    public static final String MEMBER_APPEAL_STATUS_ERROR = "单据状态错误";


    public static final String DONATE_PROJECT_TYPE_ID_IS_EMPTY = "请选择项目类别";
    public static final String DONATE_AMOUNT_IS_EMPTY = "请选择捐赠金额";
    public static final String DONATE_PROJECT_TYPE_ID_BAD_REQUEST = "无效的请求(项目类型)";
    public static final String DONATE_AMOUNT_BAD_REQUEST = "无效的请求(捐赠金额)";
    public static final String DONATE_PROJECT_ID_BAD_REQUEST = "无效的请求(项目)";

    /**
     * 阿里OSS服务器根目录占位符
     */
    public static final String REPLACE_HOLDER_PROJECT_CONTENT = "\\$ImageRoot\\$";
    public static final String WXPAY_GET_STATUS_SUCCESS = "支付状态获取成功";
    public static final String WXPAY_GET_STATUS_ORDER_NO_NO_EXISTS = "支付状态获取成功";
    public static final String WXPAY_GET_STATUS_UNPAID = "未支付";

    /**
     * 合同签订
     */
    public static final String CONTRACT_SIGN_SUCCESS = "合同签订成功";
    public static final String CONTRACT_SIGN_FAILED = "合同签订失败";
    public static final String CONTRACT_IMG_NULL = "请上传签订资料";

    /**
     * 文章
     */
    public static final String ARTICLE_GET_BY_TYPE_SUCCESS = "获取文章成功";
    public static final String ARTICLE_DETAIL_GET_SUCCESS = "获取文章详情成功";
    public static final String CLAUSE_GET_SUCCESS = "条款获取成功";

    /**
     * 受捐人个人信息页
     */
    public static final String ACCEPTER_GET_SUCCESS = "获取受捐人成功";
    public static final String ACCEPTER_NOT_EXISTS = "授号不存在";
    public static final String ACCEPTER_DISABLED = "授号已禁用";

    /**
     * 受捐人申诉列表页
     */
    public static final String ACCEPTER_APPEAL_LIST_GET_SUCCESS = "获取申诉成功";

    /**
     * 我的获捐页面
     */
    public static final String ACCEPTER_ACCEPTED_GET_SUCCESS = "获取我的获捐成功";

    /**
     * 省市区接口
     */
    public static final String AREA_GET_SUCCESS = "获取数据成功";

    /**
     * 文件上传
     */
    public static final String FILE_UPLOAD_SUCCESS = "上传成功";

    public static final String FILE_UPLOAD_FAILED = "上传失败";

}
