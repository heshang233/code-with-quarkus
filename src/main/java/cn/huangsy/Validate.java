package cn.huangsy;

import cn.huangsy.exception.ValidationException;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

import java.util.Set;

public class Validate {

    private static final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    private boolean isValid(Set<ConstraintViolation<Validate>> violations) {
        return violations.size() != 0;
    }

    @JsonIgnore
    public boolean isValid(){
        return this.isValid(validator.validate(this));
    }

    public void checkValid() {
        Set<ConstraintViolation<Validate>> violations = validator.validate(this);
        if(violations.size() != 0) {
            throw new ValidationException(this, violations);
        }
    }
}
