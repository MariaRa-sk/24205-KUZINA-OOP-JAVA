package org.example.exceptions;

/**
 * Исключение, возникающее при попытке деления на ноль.
 */
public class DivisionByZeroException extends CalculatorExeption {
    public DivisionByZeroException(){
        super("Деление на ноль!");
    }
}
