package org.example;

import java.util.Scanner;

public class Console {
    private Scanner scanner;

    public Console() {
        scanner = new Scanner(System.in);
    }

    /**
     * Выводит текст в консоль без перехода на новую строку.
     * После вывода курсор остается в конце той же строки.
     * @param text текст для вывода
     */
    public void print(String text) {
        System.out.print(text);
    }

    /**
     * Выводит текст в консоль и переходит на новую строку.
     * После вывода курсор переводится на следующую строку.
     * @param text текст для вывода
     */
    public void println(String text) {
        System.out.println(text);
    }

    /**
     * Читает строку, введенную игроком с клавиатуры.
     * Ожидает ввода и нажатия Enter.
     * @return строка, которую ввел игрок
     */
    public String read() {
        return scanner.nextLine();
    }
}
