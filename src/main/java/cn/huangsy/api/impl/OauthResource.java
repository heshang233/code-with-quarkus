package cn.huangsy.api.impl;

import cn.huangsy.model.ResponseEntityView;
import cn.huangsy.model.authentication.TokenEntity;
import cn.huangsy.model.authorize.APITokenRequest;
import cn.huangsy.utils.PemUtils;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.KeyPairBuilder;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import java.time.Instant;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

@Path("/myservice/oauth2")
public class OauthResource {

    public static KeyPair keyPair = Jwts.SIG.RS256.keyPair().build();

    @Path("token")
    @POST
    public String authorize(APITokenRequest request) {
        System.out.println(request);
        System.out.println(Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded()));
        return Jwts.builder()
                .claims()
                .issuer("me")
                .subject("Joe")
                .audience().add("you").and()
                .add("user", request.getUser())
                .and()
                .id(UUID.randomUUID().toString())
                .expiration(Date.from(Instant.now().plusSeconds(7200L)))
                .header().add("type", "JWT").and()
                .signWith(keyPair.getPrivate())
                .compact();
    }
}
