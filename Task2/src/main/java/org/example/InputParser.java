package org.example;

public class InputParser {

    /**
     * Парсит одну строку и возвращает массив токенов
     * @param line строка для парсинга
     * @return массив токенов или null, если строка пустая/комментарий
     */
    public static String[] parseLine(String line) {
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