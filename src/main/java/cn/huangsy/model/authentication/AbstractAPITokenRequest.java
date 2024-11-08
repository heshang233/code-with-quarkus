package cn.huangsy.model.authentication;

import cn.huangsy.model.annotation.ValueType;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.annotation.Nullable;
import org.immutables.value.Value;

@ValueType
@Value.Immutable
@JsonDeserialize(as = APITokenRequest.Builder.class)
public interface AbstractAPITokenRequest {
    String user();
    char[] password();

    @Nullable
    Integer expirationDays();
    @Nullable
    String label();
}
