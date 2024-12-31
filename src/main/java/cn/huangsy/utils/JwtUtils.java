package cn.huangsy.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.jackson.io.JacksonDeserializer;
import io.jsonwebtoken.lang.Maps;

import javax.crypto.SecretKey;
import java.security.Key;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtils {

    private static final long expirationTime = 60 * 60 * 24 * 7;

    /**
     * Base64 encode {"typ":"JWT","alg":"NONE"}
     */
    private static final String NONE_HEADER = "eyJ0eXAiOiJKV1QiLCJhbGciOiJOT05FIn0=";

    private static final String CLAIMS_USER = "user";
    private static final String DOT = ".";

    public static String generateToken(String uuid, Key key, JwtUser userInfo) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIMS_USER, userInfo);
        Instant now = Instant.now();
        claims.put(Claims.ISSUED_AT, Date.from(now));
        claims.put(Claims.EXPIRATION, Date.from(now.plusSeconds(expirationTime)));
        claims.put(Claims.ID, uuid);
        return Jwts.builder().claims(claims)
                .signWith(key)
                .compact();
    }

    public static JwtUser parseJWTToken(String token, PublicKey publicKey) {
        return Jwts.parser()
                .json(new JacksonDeserializer(Maps.of(CLAIMS_USER, JwtUser.class).build()))
                .verifyWith(publicKey).build()
                .parseSignedClaims(token)
                .getPayload().get(CLAIMS_USER, JwtUser.class);
    }

    public static JwtUser parseRefreshToken(String token, SecretKey publicKey) {
        Claims claims = Jwts.parser()
                .json(new JacksonDeserializer(Maps.of(CLAIMS_USER, JwtUser.class).build()))
                .verifyWith(publicKey).build()
                .parseSignedClaims(token)
                .getPayload();
        JwtUser jwtInfo = claims.get(CLAIMS_USER, JwtUser.class);
        jwtInfo.setUuid(claims.getId());
        return jwtInfo;
    }

    /**
     * 跳过签名验证
     */
    public static JwtUser parseTokenWithoutSignature(String token) {
        String[] parts = token.split("\\.");
        if (parts.length != 3) {
            throw new IllegalArgumentException("Invalid JWT token");
        }
        String noSignatureToken = NONE_HEADER.concat(DOT).concat(parts[1]).concat(DOT);
        return Jwts.parser()
                .unsecured()
                .json(new JacksonDeserializer(Maps.of(CLAIMS_USER, JwtUser.class).build()))
                .build()
                .parseUnsecuredClaims(noSignatureToken)
                .getPayload().get(CLAIMS_USER, JwtUser.class);
    }
}
