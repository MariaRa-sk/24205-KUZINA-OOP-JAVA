package org.example;

import org.example.commands.Command;
import org.example.exceptions.CalculatorExeption;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Основной класс калькулятора.
 * Управляет выполнением команд и взаимодействием с пользователем.
 */
public class Calculator {
    private final Logger log = LoggerFactory.getLogger(Calculator.class);
    private Context context;
    private CommandFactory factory;
    private InputParser parser;

    public Calculator() {
        this.context = new Context();
        this.factory = new CommandFactory();
        this.parser = new InputParser();
    }

    /**
     * Запускает калькулятор.
     * Читает команды из источника (файл или консоль) и выполняет их.
     */
    public void run(String[] args) {
        log.info("Запуск калькулятора. Args: {}", Arrays.toString(args));
        ScannerCreator creator = new ScannerCreator();

        try (Scanner scanner = creator.createScanner(args)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                log.debug("Прочитана строка: {}", line);

                String[] tokens = parser.parseLine(line);
                if (tokens == null) {
                    continue;
                }

                try {
                    Command command = factory.create(tokens[0]);
                    command.execute(tokens, context);
                } catch (CalculatorExeption e) {
                    System.err.println("Ошибка: " + e.getMessage());
                }
                log.debug("Команда {} выполнена", tokens[0]);
            }
        } catch (FileNotFoundException e) {
            log.error("Ошибка открытия файла", e);
            System.err.println("Ошибка: " + e.getMessage());
        }
        log.info("Калькулятор завершил работу");
    }
}
