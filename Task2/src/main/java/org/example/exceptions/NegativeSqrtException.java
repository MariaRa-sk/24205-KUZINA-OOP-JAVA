package org.example.exceptions;

/**
 * Исключение, возникающее при попытке извлечь квадратный корень из отрицательного числа.
 */
public class NegativeSqrtException extends CalculatorExeption{
    public NegativeSqrtException(){
        super("Нельзя извлечь квадратный корень из отрицательного числа!");
    }
}
