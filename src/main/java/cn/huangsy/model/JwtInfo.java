package cn.huangsy.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.Date;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class JwtInfo {
    /**
     * jti {@link io.jsonwebtoken.Claims#getId()}
     */
    @JsonIgnore
    String uuid;
    /**
     * exp {@link io.jsonwebtoken.Claims#getExpiration()}
     */
    @JsonIgnore
    Date exp;
    /**
     * 用户标识
     */
    Long uid;
    /**
     * 公司标识
     */
    Long cid;
    /**
     * 员工标识
     */
    Long eid;
    /**
     * 员工名称
     */
    String ename;
    /**
     * 时区
     */
    String tz;
}