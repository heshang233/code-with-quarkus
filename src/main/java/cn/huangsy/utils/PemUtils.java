package cn.huangsy.utils;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class PemUtils {
    public static final String RSA = "RSA";

    private static PublicKey getPublicKey(String pem, String algorithm) {
        try {
            byte[] keyBytes = Base64.getDecoder().decode(removeBeginEnd(pem));
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
            return keyFactory.generatePublic(keySpec);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static PublicKey decodePublicKey(String pem) {
        return getPublicKey(pem, RSA);
    }

    public static PublicKey decodePublicKey(String pem, String algorithm) {
        return getPublicKey(pem, algorithm);
    }


    private static PrivateKey getPrivateKey(String pem, String algorithm) {
        try {
            byte[] keyBytes = Base64.getDecoder().decode(removeBeginEnd(pem));
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
            return keyFactory.generatePrivate(keySpec);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static PrivateKey decodePrivateKey(String pem) {
        return getPrivateKey(pem, RSA);
    }

    public static PrivateKey decodePrivateKey(String pem, String algorithm) {
        return getPrivateKey(pem, algorithm);
    }

    public static String removeBeginEnd(String pem) {
        pem = pem.replaceAll("-----BEGIN (.*)-----", "");
        pem = pem.replaceAll("-----END (.*)----", "");
        pem = pem.replaceAll("\r\n", "");
        pem = pem.replaceAll("\n", "");
        return pem.trim();
    }


}
