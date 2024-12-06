package cn.huangsy.exception;

import cn.huangsy.model.ResponseEntityView;
import jakarta.ws.rs.core.Response;

public class BadRequestException extends HttpStatusCodeException {

    public static final String ERROR_KEY = "bad_request";

    public BadRequestException(String message) {
        this(null, message, new String[0]);
    }

    public BadRequestException(String message, String... messageArgs) {
        this(null, message, messageArgs);
    }

    public BadRequestException(Throwable cause, String message, String... messageArgs) {
        super(cause, Response.Status.BAD_REQUEST, ERROR_KEY, message, messageArgs);
    }

    public BadRequestException(Throwable cause, ResponseEntityView<Object> responseEntityView, String message) {
        super(cause, Response.Status.BAD_REQUEST, ERROR_KEY, responseEntityView, message);
    }

    public BadRequestException(Throwable cause, String key, ResponseEntityView<Object> responseEntityView, String message) {
        super(cause, Response.Status.BAD_REQUEST, key, responseEntityView, message);
    }
}
