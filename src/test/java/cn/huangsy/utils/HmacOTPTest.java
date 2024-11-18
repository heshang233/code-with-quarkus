package cn.huangsy.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.UnsupportedEncodingException;

class HmacOTPTest {

    @Test
    public void test() throws UnsupportedEncodingException {
        HmacOTP hmacOTP = new HmacOTP(8, 10, OTPAlgorithm.HmacSHA256);

        String secret = "ggFmrUD8dhzJbLioxcE8";
        String keyURI = OTPUtils.generateHOTPKeyURI(hmacOTP, secret, "stariboss", "huangsy");
        System.out.println(keyURI);
        Assertions.assertEquals("48200066", hmacOTP.generateHOTP(secret, 0));
        Assertions.assertEquals(2, hmacOTP.validateHOTP("02261323", secret, 1));
        Assertions.assertEquals(4, hmacOTP.validateHOTP("00010539", secret, 2));

    }

}