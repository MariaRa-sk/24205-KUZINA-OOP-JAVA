package org.example;

import java.util.*;

public class NumberGenerator{

    /**
     * Генерирует четырехзначное число с уникальными цифрами
     * @return строка из 4 цифр, где все цифры различны
     */
    public String generateNumber() {
        Random random = new Random();
        List<Integer> usedDigits = new ArrayList<>();
        StringBuilder result = new StringBuilder();

        while (result.length() < 4) {
            int digit = random.nextInt(10);
            if (!usedDigits.contains(digit)) {
                usedDigits.add(digit);
                result.append(digit);
            }
        }

        return result.toString();
    }
}
