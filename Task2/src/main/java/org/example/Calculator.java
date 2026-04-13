package org.example;

import org.example.commands.Command;
import org.example.exceptions.CalculatorExeption;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Основной класс калькулятора.
 * Управляет выполнением команд и взаимодействием с пользователем.
 */
public class Calculator {
    private Context context;
    private CommandFactory factory;

    public Calculator() {
        this.context = new Context();
        this.factory = new CommandFactory();
    }

    /**
     * Запускает калькулятор.
     * Читает команды из источника (файл или консоль) и выполняет их.
     */
    public void run(String[] args) {
        ScannerCreator creator = new ScannerCreator();

        try (Scanner scanner = creator.createScanner(args)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();

                if (line.isEmpty() || line.startsWith("#")) {
                    continue;
                }

                String[] tokens = line.split("\\s+");

                try {
                    Command command = factory.create(tokens[0]);
                    command.execute(tokens, context);
                } catch (CalculatorExeption e) {
                    System.err.println("Ошибка: " + e.getMessage());
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Ошибка: " + e.getMessage());
        }
    }
}