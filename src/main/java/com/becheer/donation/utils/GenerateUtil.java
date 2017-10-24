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
        return UUID.getRandomNumber(16);
    }
}
