package cn.huangsy.utils;

import org.junit.jupiter.api.Test;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.*;
import java.security.cert.CertificateException;
import java.util.Arrays;
import java.util.Base64;
import java.util.UUID;

class JwtUtilsTest {

    @Test
    public void test1() throws IOException {
        try {
            final Path path = Paths.get("/code", "code-with-quarkus", "/src/main/resources/private.pem");
            String pem = Files.readString(path);
            PrivateKey privateKey = PemUtils.decodePrivateKey(pem);
            final String uuid = UUID.randomUUID().toString();
            final JwtUser jwtUser = JwtUser.builder()
                    .uuid(uuid)
                    .uid(1L).cid(2L).eid(3L).ename("huangsy")
                    .tz("+08:00").build();
            final String token = JwtUtils.generateToken(uuid, privateKey, jwtUser);
            System.out.println(token);

            String publicKeyPem = Files.readString(Paths.get("/code/code-with-quarkus/src/main/resources/public.pem"));
            final PublicKey publicKey = PemUtils.decodePublicKey(publicKeyPem);
            final JwtUser jwtUser1 = JwtUtils.parseJWTToken(token, publicKey);
            System.out.println(jwtUser1);

        } catch (IOException exception) {

        }
    }

    @Test
    public void test2() throws IOException {
        try {

            final KeyStore keyStore = KeyStore.getInstance("PKCS12");
            keyStore.load(Files.newInputStream(Paths.get("C:\\nfs1\\keytool\\hs512.p12")), "123456".toCharArray());
            final Key key = keyStore.getKey("huangsy", "123456".toCharArray());
            System.out.println(Base64.getEncoder().encodeToString(key.getEncoded()));
            final String uuid = UUID.randomUUID().toString();
            final JwtUser jwtUser = JwtUser.builder()
                    .uuid(uuid)
                    .uid(1L).cid(2L).eid(3L).ename("huangsy")
                    .tz("+08:00").build();
            final String token = JwtUtils.generateToken(uuid, key, jwtUser);
            System.out.println(token);

            final JwtUser jwtUser1 = JwtUtils.parseRefreshToken(token, (SecretKey) key);
            System.out.println(jwtUser1);

        } catch (IOException exception) {

        } catch (KeyStoreException e) {
            throw new RuntimeException(e);
        } catch (CertificateException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (UnrecoverableKeyException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void test3() throws IOException {
        try {

            P12KeyReader keyStore = KeyStoreManager.getP12KeyReader("C:\\nfs1\\keytool\\all.p12", "123456");
            testxxx(keyStore);
            testxxx(keyStore);

            P12KeyReader keyStore2 = KeyStoreManager.getP12KeyReader("C:\\nfs1\\keytool\\all.p12", "123456");
            testxxx(keyStore2);
            testxxx(keyStore2);

        } catch (UnrecoverableKeyException e) {
            throw new RuntimeException(e);
        } catch (KeyStoreException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private static void testxxx(P12KeyReader keyStore) throws KeyStoreException, UnrecoverableKeyException, NoSuchAlgorithmException {
        Key key = keyStore.getKey("accesstoken", null);
        String uuid = UUID.randomUUID().toString();
        JwtUser jwtUser = JwtUser.builder()
                .uuid(uuid)
                .uid(1L).cid(2L).eid(3L).ename("huangsy")
                .tz("+08:00").build();
        final String token = JwtUtils.generateToken(uuid, key, jwtUser);
        System.out.println(token);

        final JwtUser jwtUser1 = JwtUtils.parseJWTToken(token, keyStore.getPublicKey("accesstoken"));
        System.out.println(jwtUser1);
    }

    @Test
    public void test8() throws NoSuchAlgorithmException {
        final KeyGenerator hmacSHA512 = KeyGenerator.getInstance("HmacSHA512");
        final SecretKey secretKey = hmacSHA512.generateKey();
        System.out.println(Base64.getEncoder().encodeToString(secretKey.getEncoded()));
    }

}