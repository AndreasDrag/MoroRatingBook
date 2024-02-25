package com.moro.rating.book.controller.validator.annotation;

import com.moro.rating.book.controller.validator.IntegerIdValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(
        validatedBy = {IntegerIdValidator.class}
)
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface IntegerId {
    String message() default "must be greater than or equal to 1";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}