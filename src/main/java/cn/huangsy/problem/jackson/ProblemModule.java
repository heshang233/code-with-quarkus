package cn.huangsy.problem.jackson;

import cn.huangsy.problem.HttpProblem;
import cn.huangsy.problem.Problem;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.util.VersionUtil;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.module.SimpleModule;

public class ProblemModule extends Module {
    @Override
    public String getModuleName() {
        return ProblemModule.class.getSimpleName();
    }

    @Override
    public Version version() {
        return VersionUtil.mavenVersionFor(ProblemModule.class.getClassLoader(),
                "cn.huangsy", "jackson-datatype-problem");
    }

    @Override
    public void setupModule(SetupContext context) {
        final SimpleModule module = new SimpleModule();
        module.setMixInAnnotation(HttpProblem.class, HttpProblemMixIn.class);
        module.setMixInAnnotation(Problem.class, ProblemMixIn.class);
        module.setupModule(context);
    }
}
