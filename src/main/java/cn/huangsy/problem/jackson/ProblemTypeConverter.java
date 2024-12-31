package cn.huangsy.problem.jackson;

import cn.huangsy.problem.Problem;
import com.fasterxml.jackson.databind.util.StdConverter;

import java.net.URI;

public class ProblemTypeConverter extends StdConverter<URI, URI> {
    @Override
    public URI convert(URI value) {
        return Problem.DEFAULT_TYPE.equals(value) ? null : value;
    }
}
