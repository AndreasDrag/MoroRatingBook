package com.moro.rating.book.controller.validator;

import jakarta.validation.ConstraintValidatorContext;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RateValidatorTest {

    private RateValidator rateValidator = new RateValidator();

    @MockBean
    private ConstraintValidatorContext constraintValidatorContext;

    @Test
    public void isValidIsTrueWhenFieldIsBetween0To5() {
        assertTrue(rateValidator.isValid(5, constraintValidatorContext));
    }

    @Test
    public void isValidIsFalseWhenFieldIsNotPositive() {
        assertFalse(rateValidator.isValid(-5, constraintValidatorContext));
    }

    @Test
    public void isValidIsFalseWhenFieldIsPositiveNotBetween0To5() {
        assertFalse(rateValidator.isValid(6, constraintValidatorContext));
    }
}