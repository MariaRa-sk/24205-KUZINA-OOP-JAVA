package org.example.exceptions;

/**
 * Исключение, возникающее при ошибках в фабрике команд.
 */
public class CommandFactoryException extends CalculatorExeption {
    public CommandFactoryException(String message) {
        super(message);
    }
}
