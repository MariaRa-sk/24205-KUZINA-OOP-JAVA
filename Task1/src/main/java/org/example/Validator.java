package org.example;

import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Validator {
    private static final Logger logger = LoggerFactory.getLogger(Validator.class);

    /**
     * Проверяет, соответствует ли ввод игрока правилам игры
     * @param input строка, которую ввел игрок
     * @return true, если ввод правильный, false если есть ошибка
     */
    public boolean isValid(String input) {
        logger.debug("Начало проверки ввода: '{}'", input);
        if (input == null || input.isEmpty()) {
            logger.warn("Ошибка: ввод равен null или пустая строка");
            return false;
        }

        if (input.length() != 4) {
            logger.warn("Ошибка: неверная длина строки ({} вместо 4)", input.length());
            return false;
        }

        for (int i = 0; i < input.length(); ++i) {
            char c = input.charAt(i);
            if (!Character.isDigit(c)) {
                logger.warn("Ошибка: найден нецифровой символ '{}' на позиции {}", c, i);
                return false;
            }
        }

        for (int i = 0; i < input.length(); ++i) {
            for (int j = i + 1; j < input.length(); ++j) {
                if (input.charAt(i) == input.charAt(j)) {
                    logger.warn("Ошибка: найдены повторяющиеся цифры '{}' на позициях {} и {}", input.charAt(i), i, j);
                    return false;
                }
            }
        }
        logger.debug("Ввод '{}' успешно прошел валидацию", input);
        return true;
    }
}
