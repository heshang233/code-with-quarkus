package cn.huangsy.utils;

import java.security.*;
import java.security.cert.Certificate;
import java.util.Objects;

public class P12KeyReader {

    private final KeyStore keyStore;

    public P12KeyReader(KeyStore keyStore) {
        this.keyStore = keyStore;
    }

    public PublicKey getPublicKey(String alias) throws KeyStoreException {
        Certificate certificate = keyStore.getCertificate(alias);
        return certificate.getPublicKey();
    }

    public Key getKey(String alias, String password) throws KeyStoreException, UnrecoverableKeyException, NoSuchAlgorithmException {
        return keyStore.getKey(alias, Objects.isNull(password) ? new char[1] : password.toCharArray());
    }
}
