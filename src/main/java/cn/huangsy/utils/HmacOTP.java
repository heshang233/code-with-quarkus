package cn.huangsy.utils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigInteger;

public class HmacOTP {

    private static final int[] DIGITS_POWER = {1, 10, 100, 1000, 10000, 100000, 1000000, 10000000, 100000000};

    protected final OTPAlgorithm algorithm;

    protected final int numberDigits;

    protected final int lookAroundWindow;

    public HmacOTP(int numberDigits, int lookAroundWindow, OTPAlgorithm algorithm) {
        this.numberDigits = numberDigits;
        this.lookAroundWindow = lookAroundWindow;
        this.algorithm = algorithm;
    }

    public int validateHOTP(String token, String key, int counter) {
        return validateHOTP(token, key.getBytes(), counter);
    }

    /**
     * @return -1 if not a match.  A positive number means successful validation.  This positive number is also the new value of the counter
     */
    public int validateHOTP(String token, byte[] key, int counter) {
        for (int newCounter = counter; newCounter <= counter + lookAroundWindow; newCounter ++) {
            String candidate = generateHOTP(key, newCounter);
            if (candidate.equals(token)) {
                return newCounter + 1;
            }
        }
        return -1;
    }

    public String generateHOTP(String key, int counter) {
        return generateHOTP(key.getBytes(), counter);
    }

    public String generateHOTP(byte[] key, int counter) {
        StringBuilder steps = new StringBuilder(Integer.toHexString(counter));

        while (steps.length() < 16) {
            steps.insert(0, "0");
        }

        return generateOTP(key, steps.toString(), numberDigits, algorithm);
    }

    public String generateOTP(byte[] key, String counter, int returnDigits, OTPAlgorithm algorithm) {
        StringBuilder counterBuilder = new StringBuilder(counter);
        while (counterBuilder.length() < 16) {
            counterBuilder.insert(0, "0");
        }
        counter = counterBuilder.toString();

        // 16进制字符转成字节数组
        byte[] msg = hexStr2Bytes(counter);

        byte[] hash = hmac_sha1(algorithm, key, msg);

        int offset = hash[hash.length - 1] & 0xf;
        int binary = ((hash[offset] & 0x7f) << 24) | ((hash[offset + 1] & 0xff) << 16) |
                ((hash[offset + 2] & 0xff) << 8) | (hash[offset + 3] & 0xff);

        int otp = binary % DIGITS_POWER[returnDigits];

        StringBuilder result = new StringBuilder(Integer.toString(otp));

        while (result.length() < returnDigits) {
            result.insert(0, "0");
        }
        return result.toString();
    }

    private byte[] hmac_sha1(OTPAlgorithm algorithm, byte[] keyBytes, byte[] text) {
        byte[] value;

        try {
            final Mac mac = Mac.getInstance(algorithm.name());
            final SecretKeySpec macKey = new SecretKeySpec(keyBytes, "RAW");

            mac.init(macKey);

            value = mac.doFinal(text);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return value;
    }

    /**
     * hex = "0000000000000520" 十六进制字符转字节数组
     * <p> "10" + hex to bigInteger (16 convert to 10) =>  295 147 905 179 352 827 168
     * <p> bigInteger to byteArray
     * <ol>
     * <li>  bigInteger to binary (10 convert to 2) => 1 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 0101 0010 0000
     * <li>  binary to byteArray (1byte存储8bit 即从后向前每八位 2进制转10进制) => [16, 0, 0, 0, 0, 0, 0, 5, 32]
     * <ol>
     *          (2 convert to 10)
     * <li>      1 0000 => 16
     * <li>      0000 0000 => 0
     * <li>      0000 0000 => 0
     * <li>      0000 0000 => 0
     * <li>      0000 0000 => 0
     * <li>      0000 0000 => 0
     * <li>      0000 0000 => 0
     * <li>      0000 0101 => 5
     * <li>      0010 0000 => 32
     * </ol>
     * </ol>
     * <p> byteArray 删除补码 => [0, 0, 0, 0, 0, 0, 5, 32]
     * <p> 替代方法 DatatypeConverter.parseHexBinary
     *
     * @param hex
     * @return
     */
    private byte[] hexStr2Bytes(String hex) {
        byte[] bArray = new BigInteger("10" + hex, 16).toByteArray();
        byte[] ret = new byte[bArray.length - 1];

        System.arraycopy(bArray, 1, ret, 0, ret.length);
        return ret;
    }
}
