package org.example;


public class InputParser {

    /**
     * Разбирает строку на токены.
     * @param line строка для разбора
     * @return массив токенов или null, если строка пустая или комментарий
     */
    public String[] parseLine(String line) {
        if (line == null) {
            return null;
        }

        String trimmed = line.trim();
        if (trimmed.isEmpty() || trimmed.startsWith("#")) {
            return null;
        }

        return trimmed.split("\\s+");
    }
}