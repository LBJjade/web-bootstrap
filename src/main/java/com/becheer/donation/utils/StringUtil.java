package com.becheer.donation.utils;

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
}
