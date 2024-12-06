package cn.huangsy.exception;

import cn.huangsy.model.ResponseEntityView;
import jakarta.ws.rs.core.Response;

public class NotFoundException extends HttpStatusCodeException {

    private static final String ERROR_KEY = "not_found";

    public NotFoundException(String message) {
        this(null, message, new String[0]);
    }

    public NotFoundException(String message, String... messageArgs) {
        this(null, message, messageArgs);
    }

    public NotFoundException(Throwable cause, String message, String... messageArgs) {
        super(cause, Response.Status.NOT_FOUND, ERROR_KEY, message, messageArgs);
    }

    public NotFoundException(Throwable cause, ResponseEntityView<Object> responseEntityView, String message) {
        super(cause, Response.Status.NOT_FOUND, ERROR_KEY, responseEntityView, message);
    }
}
