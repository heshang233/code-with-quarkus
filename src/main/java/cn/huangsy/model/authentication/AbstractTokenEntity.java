package cn.huangsy.model.authentication;

import cn.huangsy.model.annotation.ValueType;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.immutables.value.Value;

@ValueType
@Value.Immutable
@JsonDeserialize(as = TokenEntity.class)
public interface AbstractTokenEntity {
    char[] token();
}
