package cn.huangsy.problem;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import jakarta.annotation.Nullable;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.util.*;

public class ProblemBuilder {

    private static final Set<String> RESERVED_PROPERTIES = Set.of("type", "title", "status", "detail", "instance");

    URI type = Problem.DEFAULT_TYPE;
    String title;
    int statusCode = 500;
    String detail;
    URI instance;
    final Map<String, Object> headers = new LinkedHashMap<>();
    final Map<String, Object> parameters = new LinkedHashMap<>();

    ProblemBuilder() {
    }

    public ProblemBuilder withType(@Nullable URI type) {
        this.type = type;
        return this;
    }

    public ProblemBuilder withTitle(@Nullable String title) {
        this.title = title;
        return this;
    }

    public ProblemBuilder withStatus(Response.StatusType status) {
        Objects.requireNonNull(status);
        this.statusCode = status.getStatusCode();
        return this;
    }

    public ProblemBuilder withStatus(int statusCode) {
        this.statusCode = statusCode;
        return this;
    }

    public ProblemBuilder withDetail(@Nullable String detail) {
        this.detail = detail;
        return this;
    }

    public ProblemBuilder withInstance(@Nullable URI instance) {
        this.instance = instance;
        return this;
    }

    public ProblemBuilder withHeader(String headerName, Object value) {
        headers.put(headerName, value);
        return this;
    }

    /**
     * Adds custom property to the response.
     *
     * @throws IllegalArgumentException if key is any of type, title, status, detail or instance
     */
    public ProblemBuilder with(String key, Object value) throws IllegalArgumentException {
        if (RESERVED_PROPERTIES.contains(key)) {
            throw new IllegalArgumentException("Property " + key + " is reserved");
        }
        parameters.put(key, value);
        return this;
    }

    public DefaultHttpProblem build() {
        return new DefaultHttpProblem(this);
    }
}
