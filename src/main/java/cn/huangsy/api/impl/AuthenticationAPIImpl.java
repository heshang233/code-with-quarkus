package cn.huangsy.api.impl;

import cn.huangsy.api.AuthenticationAPI;
import cn.huangsy.model.MessageEntity;
import cn.huangsy.model.ResponseEntityView;
import cn.huangsy.model.authentication.TokenEntity;

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
}
