package com.becheer.donation.utils;

import sun.misc.BASE64Decoder;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;

public class StringUtil {

    //格式化金额 98765432100 TO 986,654,321.00
    public static String formatMoney(long money) {
        if (money == 0) {
            //0 直接 return
            return "0.00";
        }
        //负数处理
        String sign = money < 0 ? "-" : "";
        money = Math.abs(money);
        if (money < 100000) {
            // 绝对值不足六位，不用添加','
            return String.valueOf(money / 100);
        }
        //解决大数除不准的问题
        String stringMoney=new BigDecimal(new DecimalFormat("0.00").format(((double)money)/100)).toString();
        stringMoney = new StringBuffer(stringMoney).reverse().toString();
        char charInteger[] = stringMoney.toCharArray();
        String result = "";
        for (int i = 0; i < charInteger.length; i++) {
            result += charInteger[i];
            if ((i -2) % 3 == 0 && i != charInteger.length - 1&&i>2) {
                result += ",";
            }
        }
        return sign+new StringBuffer(result).reverse().toString();
    }

    //Base64转byte 数组
    public static byte[] base64ImgToByteArray(String fileStr) {
        try {
            BASE64Decoder decoder = new BASE64Decoder();
            byte[] bytes;
            fileStr = fileStr.split(",")[1];

            bytes = decoder.decodeBuffer(fileStr);
            for (int i = 0; i < bytes.length; ++i) {
                // 调整异常数据
                if (bytes[i] < 0) {
                    bytes[i] += 256;
                }
            }
            return bytes;
        } catch (Exception e) {
            return null;
        }
    }

}
