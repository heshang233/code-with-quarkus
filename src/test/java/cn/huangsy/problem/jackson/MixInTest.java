package cn.huangsy.problem.jackson;

import cn.huangsy.problem.HttpProblem;
import cn.huangsy.problem.Problem;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.hasEntry;

public class MixInTest {

    private final ObjectMapper mapper = new ObjectMapper()
            .registerModule(new ProblemModule());


    @Test
    void shouldDeserializeDefaultProblem() throws IOException {
        final URL resource = getResource("default.json");
        final Problem raw = mapper.readValue(resource, Problem.class);

        assertThat(raw, instanceOf(HttpProblem.class));
        final HttpProblem problem = (HttpProblem) raw;

        assertThat(problem.getType(), hasToString("https://example.org/not-out-of-stock"));
        assertThat(problem.getParameters(), hasEntry("product", "B00027Y5QG"));
    }

    private static URL getResource(final String name) {
        final ClassLoader loader = Thread.currentThread().getContextClassLoader();
        return Objects.requireNonNull(loader.getResource(name), () -> "resource " + name + " not found.");
    }
}
