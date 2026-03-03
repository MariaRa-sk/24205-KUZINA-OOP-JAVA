package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class NumberGeneratorTest {

    private final NumberGenerator generator = new NumberGenerator();

    /**
     * Тест 1: Проверяем, что длина числа всегда 4
     */
    @Test
    void testGenerateNumberLength() {
        String number = generator.generateNumber();
        assertEquals(4, number.length());
    }

    /**
     * Тест 2: Проверяем, что все символы - цифры
     */
    @Test
    void testGenerateNumberContainsOnlyDigits() {
        String number = generator.generateNumber();
        for (int i = 0; i < number.length(); i++) {
            assertTrue(Character.isDigit(number.charAt(i)));
        }
    }

    /**
     * Тест 3: Проверяем, что все цифры уникальны (нет повторений)
     */
    @Test
    void testGenerateNumberHasUniqueDigits() {
        String number = generator.generateNumber();

        for (int i = 0; i < number.length(); i++) {
            for (int j = i + 1; j < number.length(); j++) {
                assertNotEquals(number.charAt(i), number.charAt(j));
            }
        }
    }
}