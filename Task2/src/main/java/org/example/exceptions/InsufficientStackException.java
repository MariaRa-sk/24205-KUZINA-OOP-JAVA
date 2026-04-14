package org.example.exceptions;

/**
 * Исключение, возникающее при недостаточном количестве элементов в стеке.
 */
public class InsufficientStackException extends CalculatorExeption{
    public InsufficientStackException(int required, int actual){
        super("Недостаточно элементов в стеке. Нужно: " + required + ", есть: " + actual);
    }
}
