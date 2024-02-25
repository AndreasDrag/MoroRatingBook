package com.moro.rating.book.controller.validator;

import jakarta.validation.ConstraintValidatorContext;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class IntegerIdValidatorTest {

    private IntegerIdValidator integerIdValidator = new IntegerIdValidator();

    @MockBean
    private ConstraintValidatorContext constraintValidatorContext;

    @Test
    public void isValidIsTrueWhenFieldIsPositive() {
        assertTrue(integerIdValidator.isValid(5, constraintValidatorContext));
    }

    @Test
    public void isValidIsFalseWhenFieldIsNotPositive() {
        assertFalse(integerIdValidator.isValid(-5, constraintValidatorContext));
    }

}