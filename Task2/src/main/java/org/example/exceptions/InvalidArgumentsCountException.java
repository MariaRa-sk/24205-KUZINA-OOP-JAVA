package org.example.exceptions;

public class InvalidArgumentsCountException extends CalculatorExeption{
    public InvalidArgumentsCountException(String command, int expected, int actual) {
        super("Команда " + command + " ожидает " + expected + " аргумент(a), получено " + actual);
    }
}
