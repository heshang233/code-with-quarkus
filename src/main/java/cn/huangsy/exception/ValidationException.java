package cn.huangsy.exception;

import cn.huangsy.Validate;
import cn.huangsy.model.ErrorEntity;
import cn.huangsy.model.ResponseEntityView;
import jakarta.validation.ConstraintViolation;

import java.util.List;
import java.util.Set;

public class ValidationException extends BadRequestException {

    private static final String ERROR_KEY = "invalid_parameter";

    transient public final Validate validate;

    transient public final Set<ConstraintViolation<Validate>> violations;

    public ValidationException(Validate validate, Set<ConstraintViolation<Validate>> violations) {
        super(null, ERROR_KEY, toResponseEntity(violations), null);
        this.validate = validate;
        this.violations = violations;
    }

    public static ResponseEntityView<Object> toResponseEntity(Set<ConstraintViolation<Validate>> violations) {
        List<ErrorEntity> errorEntityList = violations.stream()
                .map(constraint -> ErrorEntity.builder()
                        // todo huangsy message to i18?
                        .message(constraint.getMessage())
                        .fieldName(constraint.getPropertyPath().toString())
                        .build()).toList();
        return ResponseEntityView.builder().errors(errorEntityList).build();
    }
}
