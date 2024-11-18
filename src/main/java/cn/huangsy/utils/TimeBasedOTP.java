package cn.huangsy.utils;

import java.time.Instant;

public class TimeBasedOTP extends HmacOTP {

    protected final int interval;

    public TimeBasedOTP(int numberDigits, int lookAroundWindow, int timeIntervalInSeconds, OTPAlgorithm algorithm) {
        super(numberDigits, lookAroundWindow, algorithm);
        this.interval = timeIntervalInSeconds;
    }

    public String generateTOTP(String secretKey) {
        return generateTOTP(secretKey.getBytes());
    }

    public String generateTOTP(byte[] secretKey) {
        StringBuilder steps = new StringBuilder(Long.toHexString(getCurrentInterval()).toUpperCase());
        while (steps.length() < 16) {
            steps.insert(0, "0");
        }
        return generateOTP(secretKey, steps.toString(), this.numberDigits, this.algorithm);
    }

    public boolean validateTOTP(String token, String secret) {
        return validateTOTP(token, secret.getBytes());
    }

    public boolean validateTOTP(String token, byte[] secret) {
        long currentInterval = getCurrentInterval();

        for (int i = 0; i < (lookAroundWindow * 2); i++) {
            long delta = clockSkewIndexToDelta(i);
            long adjustedInterval = currentInterval + delta;

            StringBuilder steps = new StringBuilder(Long.toHexString(getCurrentInterval()).toUpperCase());
            while (steps.length() < 16) {
                steps.insert(0, "0");
            }

            String candidate = generateOTP(secret, steps.toString(), this.numberDigits, this.algorithm);
            if (candidate.equals(token)) {
                return true;
            }
        }
        return false;
    }

    /**
     * maps 0, 1, 2, 3, 4, 5, 6, 7, ... to 0, -1, 1, -2, 2, -3, 3, ...
     */
    private long clockSkewIndexToDelta(int idx) {
        return (idx + 1) / 2 * (1 - (idx % 2) * 2);
    }


    public long getCurrentInterval() {
        return Instant.now().getEpochSecond() / this.interval;
    }
}
