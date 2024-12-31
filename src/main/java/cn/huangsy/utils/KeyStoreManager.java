package cn.huangsy.utils;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyStore;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class KeyStoreManager {

    private static final ConcurrentHashMap<String, P12KeyReader> p12KeyReaderCache = new ConcurrentHashMap<>();

    public static P12KeyReader getP12KeyReader(String keyPath, String password) {
        return p12KeyReaderCache.computeIfAbsent(keyPath, path -> {
            try {
                KeyStore keyStore = KeyStore.getInstance("PKCS12");
                keyStore.load(Files.newInputStream(Paths.get(keyPath)), Objects.isNull(password) ?
                        new char[1] : password.toCharArray());
                return new P12KeyReader(keyStore);
            } catch (Exception e) {
                throw new RuntimeException("Failed to load p12 key file from path: " + path, e);
            }
        });
    }
}
