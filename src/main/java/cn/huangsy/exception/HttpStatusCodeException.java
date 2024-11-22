package cn.huangsy.exception;

import cn.huangsy.model.ErrorEntity;
import cn.huangsy.model.ResponseEntityView;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.Optional;

public class HttpStatusCodeException extends WebApplicationException {

    HttpStatusCodeException(Response.Status status, String key, String message, String... messageArgs) {
        this(null, status, key, message, messageArgs);
    }

    HttpStatusCodeException(Throwable cause, Response.Status status, String key, String message, String... messageArgs) {
        super(cause, toResponse(status, key, getFormattedMessage(message, messageArgs)));
    }

    HttpStatusCodeException(Throwable cause, Response.Status status, String key, ResponseEntityView<Object> responseEntity, String message) {
        super(cause, toResponse(status, responseEntity, key, message));
    }

    private static String getFormattedMessage(String message, String... messageArgs) {
        return Optional.ofNullable(message)
                .map(m -> Optional.ofNullable(messageArgs)
                        .map(args -> String.format(m, (Object[])args))
                        .orElse(m))
                .orElse(null);
    }

    private static Response toResponse(Response.Status status,
                                       String key,
                                       String message) {

        ResponseEntityView<Object> responseEntity = ResponseEntityView.builder()
                .addErrors(ErrorEntity.builder().errorCode(key).message(message).build())
                .build();
        return Response.status(status)
                .header("error-key", key)
                .header("error-message", message)
                .entity(responseEntity)
                .type(MediaType.APPLICATION_JSON_TYPE)
                .build();
    }

    private static Response toResponse(Response.Status status,
                                       ResponseEntityView<Object> responseEntity,
                                       String key,
                                       String message) {
        return Response.status(status)
                .header("error-key", key)
                .header("error-message", message)
                .entity(responseEntity)
                .type(MediaType.APPLICATION_JSON_TYPE)
                .build();
    }
}
