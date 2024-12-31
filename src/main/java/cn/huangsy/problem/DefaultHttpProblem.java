package cn.huangsy.problem;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;

import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DefaultHttpProblem extends HttpProblem {

    private final URI type;
    private final String title;
    private final int statusCode;
    private final String detail;
    private final URI instance;
    private final Map<String, Object> parameters;
    private final Map<String, Object> headers;

    @Override
    public URI getType() {
        return type;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public int getStatusCode() {
        return statusCode;
    }

    @Override
    public String getDetail() {
        return detail;
    }

    @Override
    public URI getInstance() {
        return instance;
    }

    @Override
    public Map<String, Object> getParameters() {
        return parameters;
    }

    @Override
    public Map<String, Object> getHeaders() {
        return headers;
    }

    protected DefaultHttpProblem(ProblemBuilder builder) {
        super(createMessage(builder.title, builder.detail));

        this.type = builder.type;
        this.title = builder.title;
        this.statusCode = builder.statusCode;
        this.detail = builder.detail;
        this.instance = builder.instance;
        this.parameters = Collections.unmodifiableMap(Optional.of(builder.parameters).orElseGet(LinkedHashMap::new));
        this.headers = Collections.unmodifiableMap(Optional.of(builder.headers).orElseGet(LinkedHashMap::new));
    }

    private static String createMessage(String title, String detail) {
        return Stream.of(title, detail)
                .filter(Objects::nonNull)
                .collect(Collectors.joining(": "));
    }
}
