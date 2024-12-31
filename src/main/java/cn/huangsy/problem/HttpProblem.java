package cn.huangsy.problem;

public class HttpProblem extends RuntimeException implements Problem {

    protected HttpProblem() {
        this(null);
    }

    protected HttpProblem(String message) {
        super(message);
    }

    public static ProblemBuilder builder(Problem original) {
        ProblemBuilder builder = Problem.builder()
                .withType(original.getType())
                .withInstance(original.getInstance())
                .withTitle(original.getTitle())
                .withStatus(original.getStatusCode())
                .withDetail(original.getDetail());
        original.getParameters().forEach(builder::with);
        original.getHeaders().forEach(builder::withHeader);
        return builder;
    }

}
