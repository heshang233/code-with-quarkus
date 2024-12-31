package cn.huangsy.problem.jackson;

import cn.huangsy.problem.DefaultHttpProblem;
import cn.huangsy.problem.HttpProblem;
import cn.huangsy.problem.Problem;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.net.URI;
import java.util.Map;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        property = "type",
        defaultImpl = DefaultHttpProblem.class,
        visible = true)
@JsonInclude(NON_EMPTY)
public interface ProblemMixIn extends Problem {

    @JsonProperty("type")
    @JsonSerialize(converter = ProblemTypeConverter.class)
    @Override
    URI getType();

    @JsonProperty("title")
    @Override
    String getTitle();

    @JsonProperty("status")
    @Override
    int getStatusCode();

    @JsonProperty("detail")
    @Override
    String getDetail();

    @JsonProperty("instance")
    @Override
    URI getInstance();

    @JsonAnyGetter
    @Override
    Map<String, Object> getParameters();

    @JsonIgnore
    @Override
    Map<String, Object> getHeaders();
}
