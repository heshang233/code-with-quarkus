package cn.huangsy.model;

import cn.huangsy.model.annotation.ValueType;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.smallrye.common.constraint.Nullable;
import org.immutables.value.Value;


@JsonDeserialize(builder = ErrorEntity.Builder.class)
@ValueType
@Value.Immutable
public interface AbstractErrorEntity {

    @Nullable
    @JsonProperty("error_code")
    String errorCode();
    @Nullable
    String message();
    @Nullable
    @JsonProperty("field_name")
    String fieldName();

    @Nullable
    String stacktrace();
}
