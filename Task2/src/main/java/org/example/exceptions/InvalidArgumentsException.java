package org.example.exceptions;

public class InvalidArgumentsException extends CalculatorExeption{
    public InvalidArgumentsException(String command, String paramName, String message){
        super(command + ": неверный аргумент: " + paramName + message);
    }
}
