package cn.huangsy.model;

import lombok.*;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AuthorizationDTO {

    private String accessToken;

    private Long expiresIn;

    private String tokenType;

    private String refreshToken;

    private Long refreshExpiresIn;
}
