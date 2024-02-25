package com.moro.rating.book.controller.validator;

import com.moro.rating.book.controller.validator.annotation.Rate;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class RateValidator implements ConstraintValidator<Rate, Integer> {

    private static final Pattern RATE_NUMBER_RANGE_PATTERN = Pattern.compile("[0-5]");

    public RateValidator() {
    }

    public void initialize(Rate constraintAnnotation) {
    }

    public boolean isValid(Integer field, ConstraintValidatorContext constraintValidatorContext) {
        return RATE_NUMBER_RANGE_PATTERN.matcher(String.valueOf(field)).matches();
    }
}