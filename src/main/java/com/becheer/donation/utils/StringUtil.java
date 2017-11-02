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

    public static String formatMoney(long money){
        if (money==0){
            return "0.00";
        }
        if (Math.abs(money)<100000){
            return String.valueOf(money/100);
        }
        String sign=money<0?"-":"";
        money=Math.abs(money);
        String stringMoney=String.valueOf(money/100);
        String stringDecimnal=stringMoney.substring(stringMoney.length()-1);
        String stringInteger=stringMoney.substring(0,stringMoney.indexOf('.')-1);
        stringInteger=new StringBuffer(stringInteger).reverse().toString();
        char charInteger[] = stringInteger.toCharArray();
        String result="";
        for(int i=0;i<charInteger.length;i++){
            result+=charInteger[i];
            if ((i+1)%3==0&&i!=charInteger.length-1){
                result+=",";
            }
        }
    }
}
