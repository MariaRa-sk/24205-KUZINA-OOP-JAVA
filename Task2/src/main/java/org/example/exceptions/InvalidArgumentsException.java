package org.example.exceptions;

/**
 * Исключение, возникающее при передаче неверного аргумента команде.
 */
public class InvalidArgumentsException extends CalculatorExeption{
    public InvalidArgumentsException(String command, String paramName, String message){
        super(command + ": неверный аргумент: " + paramName + message);
    }
}
