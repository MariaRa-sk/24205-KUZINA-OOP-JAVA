package org.example.exceptions;

public class InsufficientStackException extends CalculatorExeption{
    public InsufficientStackException(int required, int actual){
        super("Недостаточно элементов в стеке. Нужно: " + required + ", есть: " + actual);
    }
}
