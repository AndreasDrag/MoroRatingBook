package com.moro.rating.book.controller.validator;

import com.moro.rating.book.controller.validator.annotation.IntegerId;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class IntegerIdValidator implements ConstraintValidator<IntegerId, Integer> {
    public IntegerIdValidator() {
    }

    public void initialize(IntegerId constraintAnnotation) {
    }

    public boolean isValid(Integer field, ConstraintValidatorContext constraintValidatorContext) {
        return field == null || field > 0;
    }
}