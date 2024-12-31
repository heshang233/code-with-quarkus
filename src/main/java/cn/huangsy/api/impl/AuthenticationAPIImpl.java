package cn.huangsy.api.impl;

import cn.huangsy.api.AuthenticationAPI;
import cn.huangsy.model.*;
import cn.huangsy.model.authentication.TokenEntity;
import cn.huangsy.problem.Problem;
import cn.huangsy.utils.JwtUtils;
import cn.huangsy.utils.RSAKeyManager;
import jakarta.annotation.Resource;
import jakarta.ws.rs.core.Response;

import javax.crypto.spec.SecretKeySpec;
import java.security.PrivateKey;
import java.time.Instant;
import java.util.Base64;
import java.util.Objects;
import java.util.UUID;

public class AuthenticationAPIImpl implements AuthenticationAPI {
    @Override
    public ResponseEntityView<TokenEntity> getToken() {
        final TokenEntity tokenEntity = TokenEntity.builder()
            .token('A')
            .build();

        return ResponseEntityView.<TokenEntity>builder()
            .entity(tokenEntity)
            .addMessages(MessageEntity.builder().message("hello world!").build())
            .build();
    }

//    @Resource
//    SystemConfig systemConfig;
//
//    @Resource
//    StringRedisTemplate stringRedisTemplate;

//    @Override
//    public AuthorizationDTO login(UserLoginDTO loginDTO) {
//        String username = "admin";
//        String password = "password";
//
//        if (!Objects.equals(username, loginDTO.getUsername()) ||
//                !Objects.equals(password, loginDTO.getPassword())) {
//            throw Problem.builder().withTitle("无效用户名密码").build();
//        }
//
//        PrivateKey privateKey = RSAKeyManager.getPrivateKey("path/private.pem");
//        JwtInfo jwtInfo = JwtInfo.builder()
//                .uuid(UUID.randomUUID().toString())
//                .uid(1L).cid(2L).eid(3L).ename("huangsy")
//                .tz("+08:00").build();
//        String accessToken = JwtUtils.generateToken(privateKey, jwtInfo);
//
//        SecretKeySpec secretKey = getRefreshSecret();
//        Long refreshExpiresIn = systemConfig.getRefreshTokenExpirationTime();
//        String refreshToken = JwtUtils.generateToken(secretKey, jwtInfo, refreshExpiresIn);
//        return cacheToken(refreshToken, jwtInfo, refreshExpiresIn, expiresIn, accessToken);
//    }
//
//    @Override
//    public void logout(String uuid) {
//        Long userId = AppSession.getUserId();
//        stringRedisTemplate.opsForSet().remove(getWebUserAccessTokenRelationKey(userId), uuid);
//        stringRedisTemplate.delete(getWebAccessTokenKey(uuid));
//    }
//
//    @Override
//    public AuthorizationDTO refresh(String refreshToken) {
//        SecretKeySpec secretKey = getRefreshSecret();
//        try {
//            JwtInfo jwtInfo = JwtUtils.parseRefreshToken(refreshToken, secretKey);
//            Instant expInstant = jwtInfo.getExp().toInstant();
//            Long refreshExpiresIn = ChronoUnit.SECONDS.between(expInstant, Instant.now());
//            Long expiresIn = systemConfig.getJwtTokeExpirationTime();
//            if (expInstant.isAfter(Instant.now().minus(expiresIn, ChronoUnit.SECONDS))) {
//                refreshExpiresIn = systemConfig.getRefreshTokenExpirationTime();
//                refreshToken = JwtUtils.generateToken(secretKey, jwtInfo, refreshExpiresIn);
//            }
//            PrivateKey privateKey = RSAKeyManager.getPrivateKey(systemConfig.getAuthorizePrivateKeyPath());
//            String accessToken = JwtUtils.generateToken(privateKey, jwtInfo, expiresIn);
//            return cacheToken(refreshToken, jwtInfo, refreshExpiresIn, expiresIn, accessToken);
//        } catch (Exception e) {
//            throw Problem.builder()
//                    .withStatus(Response.Status.UNAUTHORIZED)
//                    .withTitle("Invalid refresh token.")
//                    .withDetail(e.getMessage())
//                    .build();
//        }
//    }
//
//    private AuthorizationDTO cacheToken(String refreshToken, JwtInfo jwtInfo, Long refreshExpiresIn,
//                                        Long expiresIn, String accessToken) {
//        stringRedisTemplate.opsForValue().set(getWebAccessTokenKey(jwtInfo.getUuid()), accessToken, expiresIn, TimeUnit.SECONDS);
//        stringRedisTemplate.opsForValue().set(getWebRefreshTokenKey(jwtInfo.getUuid()), refreshToken, refreshExpiresIn, TimeUnit.SECONDS);
//        String webUserAccessTokenRelationKey = getWebUserAccessTokenRelationKey(jwtInfo.getUid());
//        stringRedisTemplate.opsForSet().add(webUserAccessTokenRelationKey, jwtInfo.getUuid());
//        stringRedisTemplate.expire(webUserAccessTokenRelationKey, refreshExpiresIn, TimeUnit.SECONDS);
//        return AuthorizationDTO.builder()
//                .accessToken(accessToken)
//                .refreshToken(refreshToken)
//                .expiresIn(expiresIn)
//                .refreshExpiresIn(refreshExpiresIn)
//                .tokenType("Bearer")
//                .build();
//    }
//
//    private SecretKeySpec getRefreshSecret() {
//        return new SecretKeySpec(Base64.getDecoder().decode(systemConfig.getRefreshTokenSecretKey()), "HmacSHA512");
//    }
}
