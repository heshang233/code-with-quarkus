package cn.huangsy.utils;

public enum OTPAlgorithm {

    HmacSHA1("SHA1"), HmacSHA256("SHA256"), HmacSHA512("SHA512");

    private final String alg;

    OTPAlgorithm(String alg) {
        this.alg = alg;
    }

    public String getAlg() {
        return alg;
    }
}
