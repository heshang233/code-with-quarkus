package cn.huangsy.model.authorize;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class APITokenRequest {

    private String user;

    private String password;

    private Integer expirationDays;

}
