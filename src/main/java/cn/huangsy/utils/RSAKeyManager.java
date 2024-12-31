package cn.huangsy.utils;


import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.concurrent.ConcurrentHashMap;

public class RSAKeyManager {

    private static final ConcurrentHashMap<String, PrivateKey> privateKeyCache = new ConcurrentHashMap<>();
    private static final ConcurrentHashMap<String, PublicKey> publicKeyCache = new ConcurrentHashMap<>();

    public static PrivateKey getPrivateKey(String keyPath) {
        return privateKeyCache.computeIfAbsent(keyPath, path -> {
            try {
                // 加载并解析私钥文件
                String pem = Files.readString(Paths.get(keyPath));
                return PemUtils.decodePrivateKey(pem);
            } catch (Exception e) {
                throw new RuntimeException("Failed to load private key from path: " + path, e);
            }
        });
    }

    public static PublicKey getPublicKey(String keyPath) {
        return publicKeyCache.computeIfAbsent(keyPath, path -> {
            try {
                // 加载并解析公钥文件
                String pem = Files.readString(Paths.get(keyPath));
                return PemUtils.decodePublicKey(pem);
            } catch (Exception e) {
                throw new RuntimeException("Failed to load public key from path: " + path, e);
            }
        });
    }
}
