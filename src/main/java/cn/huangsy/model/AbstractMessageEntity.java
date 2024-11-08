package cn.huangsy.model;

import cn.huangsy.model.annotation.ValueType;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.smallrye.common.constraint.Nullable;
import org.immutables.value.Value;

@JsonDeserialize(builder = MessageEntity.Builder.class)
@Value.Immutable
@ValueType
public interface AbstractMessageEntity {

    @Nullable
    String message();
}
