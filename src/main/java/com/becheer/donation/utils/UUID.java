package com.becheer.donation.utils;


import java.util.Arrays;
import java.util.Random;

/**
 * 封装UUID
 */
public abstract class UUID {

    /**
     *生成19位UUID 13位时间戳+6位随机数
     */
    public static long GetInt64UUID(){
        return System.currentTimeMillis()*1000000+ Integer.parseInt(getRandomNumber(6));
    }

    /**
     * 生成随机数
     */
    public static String getRandomNumber(int size) {
        String num = "";

        for (int i = 0; i < size; ++i) {
            double a = Math.random() * 9.0D;
            a = Math.ceil(a);
            int randomNum = (new Double(a)).intValue();
            num = num + randomNum;
        }
        return num;
    }

}