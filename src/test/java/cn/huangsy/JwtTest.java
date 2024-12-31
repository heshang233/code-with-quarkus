package cn.huangsy;

import cn.huangsy.utils.PemUtils;
import io.jsonwebtoken.Jwts;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.PrivateKey;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;

public class JwtTest {

    @Test
    public void test() throws IOException {
        String pem = Files.readString(Paths.get("/code/code-with-quarkus/src/main/resources/private.pem"), StandardCharsets.UTF_8);
        PrivateKey privateKey = PemUtils.decodePrivateKey(pem);
        String jwt = Jwts.builder()
                .claims()
                .issuer("me")
                .subject("Joe")
                .audience().add("you").and()
                .add("user", "huangsy")
                .and()
                .id(UUID.randomUUID().toString())
                .expiration(Date.from(Instant.now().plusSeconds(7200L)))
                .header().add("type", "JWT").and()
                .signWith(privateKey)
                .compact();
        System.out.println(jwt);
    }
}
