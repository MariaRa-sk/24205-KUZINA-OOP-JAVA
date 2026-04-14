package org.example.exceptions;

/**
 * Базовый класс для всех исключений калькулятора.
 */
public class CalculatorExeption extends RuntimeException{
    public CalculatorExeption(String message){
        super(message);
    }
}
