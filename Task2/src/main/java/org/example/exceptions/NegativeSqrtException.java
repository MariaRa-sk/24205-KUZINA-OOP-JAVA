package org.example.exceptions;

public class NegativeSqrtException extends CalculatorExeption{
    public NegativeSqrtException(){
        super("Нельзя извлечь квадратный корень из отрицательного числа!");
    }
}
