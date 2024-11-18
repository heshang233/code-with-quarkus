package cn.huangsy.utils;

import org.junit.jupiter.api.Test;

import java.io.UnsupportedEncodingException;

class TimeBasedOTPTest {

    @Test
    public void test() throws UnsupportedEncodingException, InterruptedException {

        TimeBasedOTP hmacOTP = new TimeBasedOTP(6, 2, 30, OTPAlgorithm.HmacSHA256);

        String secret = "ggFmrUD8dhzJbLioxcE8";
        String keyURI = OTPUtils.generateTOTPKeyURI(hmacOTP, secret, "stariboss", "huangsy");
        System.out.println(keyURI);

        for (int i = 0; i < 200; i++) {
            System.out.println(hmacOTP.generateTOTP(secret));
            Thread.sleep(1000);
        }
    }

}