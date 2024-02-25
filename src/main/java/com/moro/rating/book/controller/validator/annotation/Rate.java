package com.moro.rating.book.controller.validator.annotation;

import com.moro.rating.book.controller.validator.RateValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(
        validatedBy = {RateValidator.class}
)
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Rate {
    String message() default "Rate must be between 0-5.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}