package cn.huangsy.problem;

import jakarta.ws.rs.core.MediaType;

import java.net.URI;
import java.util.Collections;
import java.util.Map;

public interface Problem {

    MediaType MEDIA_TYPE = new MediaType("application", "problem+json");

    URI DEFAULT_TYPE = URI.create("about:blank");

    default URI getType() {
        return DEFAULT_TYPE;
    }

    default String getTitle() {
        return null;
    }

    default int getStatusCode() {
        return 500;
    }

    default String getDetail() {
        return null;
    }

    default URI getInstance() {
        return null;
    }

    default Map<String, Object> getParameters() {
        return Collections.emptyMap();
    }

    default Map<String, Object> getHeaders() {
        return Collections.emptyMap();
    }

    static ProblemBuilder builder() {
        return new ProblemBuilder();
    }
}
