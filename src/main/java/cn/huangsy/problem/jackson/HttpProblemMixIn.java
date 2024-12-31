package cn.huangsy.problem.jackson;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nullable;

import java.net.URI;

public class HttpProblemMixIn {

    @JsonCreator
    HttpProblemMixIn(@Nullable @JsonProperty("type") final URI type,
                     @Nullable @JsonProperty("title") final String title,
                     @Nullable @JsonProperty("status") final int statusCode,
                     @Nullable @JsonProperty("detail") final String detail,
                     @Nullable @JsonProperty("instance") final URI instance) {

    }
}
