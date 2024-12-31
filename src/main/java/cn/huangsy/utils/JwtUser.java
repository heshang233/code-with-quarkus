package cn.huangsy.utils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class JwtUser {
    @JsonIgnore
    String uuid;
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