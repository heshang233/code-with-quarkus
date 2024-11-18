package cn.huangsy.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;

public class OTPUtils {

    public static String generateSecret(int length) {
        String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVW1234567890";
        SecureRandom r = new SecureRandom();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            char c = chars.charAt(r.nextInt(chars.length()));
            sb.append(c);
        }
        return sb.toString();
    }

    /**
     * Constructs the <code>otpauth://</code> URI based on the <a href="https://github.com/google/google-authenticator/wiki/Key-Uri-Format">Key-Uri-Format</a>.
     *
     * @return the <code>otpauth://</code> URI
     */
    public static String generateHOTPKeyURI(HmacOTP hmacOTP, String secret,
                                            String issuerName, String accountName) throws UnsupportedEncodingException {

        issuerName = URLEncoder.encode(issuerName, StandardCharsets.UTF_8) .replaceAll("\\+", "%20");
        accountName = URLEncoder.encode(accountName, StandardCharsets.UTF_8);

        return "otpauth://hotp/".concat(issuerName).concat(":").concat(accountName).concat("?")
                .concat("secret=").concat(Base32.encode(secret.getBytes()))
                .concat("&digits=").concat(String.valueOf(hmacOTP.numberDigits))
                .concat("&algorithm=").concat(hmacOTP.algorithm.getAlg())
                .concat("&issuer=").concat(issuerName)
                .concat("&counter=").concat(String.valueOf(0));

    }

    public static String generateTOTPKeyURI(TimeBasedOTP hmacOTP, String secret,
                                        String issuerName, String accountName) throws UnsupportedEncodingException {

        issuerName = URLEncoder.encode(issuerName, StandardCharsets.UTF_8) .replaceAll("\\+", "%20");
        accountName = URLEncoder.encode(accountName, StandardCharsets.UTF_8);

        return "otpauth://totp/".concat(issuerName).concat(":").concat(accountName).concat("?")
                .concat("secret=").concat(Base32.encode(secret.getBytes()))
                .concat("&digits=").concat(String.valueOf(hmacOTP.numberDigits))
                .concat("&algorithm=").concat(hmacOTP.algorithm.getAlg())
                .concat("&issuer=").concat(issuerName)
                .concat("&period=").concat(String.valueOf(hmacOTP.interval));

    }
}
