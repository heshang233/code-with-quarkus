package cn.huangsy.exception;

import cn.huangsy.model.ErrorEntity;
import cn.huangsy.model.ResponseEntityView;
import cn.huangsy.utils.ConfigUtils;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import lombok.extern.slf4j.Slf4j;

import java.io.PrintWriter;
import java.io.StringWriter;

@Provider
@Slf4j
public class ExceptionHandler implements ExceptionMapper<Throwable> {

    @Override
    public Response toResponse(Throwable throwable) {
        log.warn(throwable.getMessage(), throwable);
        return createResponse(throwable, Response.Status.INTERNAL_SERVER_ERROR);
    }

    public static Response createResponse(Throwable throwable, Response.Status status) {
        if (throwable instanceof WebApplicationException exception) {
            return exception.getResponse();
        }

        ErrorEntity.Builder builder = ErrorEntity.builder().message(throwable.getMessage());
        if (ConfigUtils.isDevMode()) {
            StringWriter errors = new StringWriter();
            try (PrintWriter printWriter = new PrintWriter(errors)) {
                throwable.printStackTrace(printWriter);
            }
            builder.stacktrace(errors.toString());
        }
        ResponseEntityView<Object> responseEntityView = ResponseEntityView.builder()
                .addErrors(builder.build())
                .build();

        return Response
                .status(status)
                .entity(responseEntityView)
                .build();
    }
}
