package org.example;

/**
 * Точка входа в программу.
 * Создает объект калькулятора и запускает его.
 */
public class Main {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        calculator.run(args);
    }
}