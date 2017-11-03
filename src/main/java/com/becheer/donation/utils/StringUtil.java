package com.becheer.donation.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class StringUtil {

    public static String formatCurrency(String currenty) {
        String [] money=currenty.split("\\.");
//        String str = currenty.split("\\.")[0];
//        String str = currenty.split("\\.")[1];

        String str=money[0];
        String spot="";
        try{
            spot=money[1];
        }catch(Exception e){
           System.out.println( e.getMessage());
        }
        String get = "", lost = "";
        String  num = "";
        int length = str.length();
        while (length > 0) {
            if (length > 3) {
                get = str.substring(length - 4, length - 1);
                lost = str.substring(0, length - 3);
                str = lost;
                if (num == "") {
                    num = get;
                } else {
                    num = get + "," + num;
                }

                length = length - 3;
            } else {
                if (lost == "") {
                    num = str;
                } else {
                    num = lost + "," + num;
                }
                length = length - 3;
            }
        }
        if (spot == "") {
            num = num + ".00";
        } else {
            if (spot.length() > 2) {
                spot = spot.substring(0, 2);
                num = num + "." + spot;
            } else if (spot.length() == 2) {
                num = num + "." + spot;
            } else {
                num = num + "." + spot + "0";
            }
        }
        return num;
    }

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
}
