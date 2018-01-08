package com.becheer.donation.utils;

public class GenerateUtil {
    public static String genNoContractDonateNo() {
        return UUID.getRandomNumber(5);
    }

    // TODO: 需要完成自动生成支付计划标题的方法
    public static String genPaymentPlanTitle(String title) {
        return title;
    }

    public static String genOrderNo() {
        return "DNT_" + UUID.GetInt64UUID();
    }

    public static String genLoginCookie(long userId){
        String result=null;
        result=HashUtil.SHA256(String.valueOf(userId));
        result="bc_member_cookie"+result;
        StringBuffer stringBuffer=new StringBuffer(result);
        result=stringBuffer.reverse().toString();
        result=HashUtil.MD5(result);
        return result;
    }
}
