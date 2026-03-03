package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

class ValidatorTest {

    private Validator validator = new Validator();

    @ParameterizedTest
    @ValueSource(strings = {"1234", "5678", "9081", "3742", "0123", "9876"})
    void testValidInputs(String input) {
        assertTrue(validator.isValid(input));
    }

    @ParameterizedTest
    @ValueSource(strings = {"123", "12345", "", "12a4", "1123", "1111", "1 23"})
    void testInvalidInputs(String input) {
        assertFalse(validator.isValid(input));
    }

    @Test
    void testNullInput() {
        assertFalse(validator.isValid(null));
    }
}