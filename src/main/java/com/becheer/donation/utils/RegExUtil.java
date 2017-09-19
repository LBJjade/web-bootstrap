package com.becheer.donation.utils;

import java.util.regex.Pattern;

/*
* RegExUtil 正则表达式验证类
* Creator : xiaokepu
* Date : 2017-09-15
*/
public class RegExUtil {

    /**
     * 验证Email
     * @param email
     * @return
     */
    public static boolean checkEmail(String email) {
        String regex = "\\w+@\\w+\\.[a-z]+(\\.[a-z]+)?";
        return Pattern.matches(regex, email);
    }

    /**
     * 验证身份证
     * @param idCard
     * @return
     */
    public static boolean checkIdCard(String idCard) {
        String regex = "[1-9]\\d{13,16}[a-zA-Z0-9]{1}";
        return Pattern.matches(regex,idCard);
    }

    /**
     * 验证手机号
     * @param mobile
     * @return
     */
    public static boolean checkMobile(String mobile) {
        String regex = "1[34578]\\d{9}$";
        return Pattern.matches(regex,mobile);
    }
}
