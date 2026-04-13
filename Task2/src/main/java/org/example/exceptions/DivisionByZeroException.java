package org.example.exceptions;

public class DivisionByZeroException extends CalculatorExeption {
    public DivisionByZeroException(){
        super("Деление на ноль!");
    }
}
