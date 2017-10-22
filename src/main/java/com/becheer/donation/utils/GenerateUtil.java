package com.becheer.donation.utils;

public class GenerateUtil {
    public static String genNoContractDonateNo() {
        return UUID.getRandomNumber(5);
    }
}
