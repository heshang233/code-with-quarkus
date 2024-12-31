package cn.huangsy.model;

import lombok.*;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginDTO {

    private String username;

    private String password;
}
