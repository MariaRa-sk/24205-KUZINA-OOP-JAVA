package org.example;

import org.example.commands.*;
import org.example.exceptions.*;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;


public class Parser {
    public static void main(String[] args) {
        Scanner input;

        if (args.length > 0) {
            try {
                input = new Scanner(new File(args[0]));
            } catch (FileNotFoundException e) {
                System.err.println("Файл не найден: " + args[0]);
                return;
            }
        } else {
            input = new Scanner(System.in);
        }

        Context context = new Context();
        CommandFactory factory = new CommandFactory();

        while (input.hasNextLine()) {
            String line = input.nextLine().trim();   //trim() удаляет пробелы в начале

            if (line.isEmpty() || line.startsWith("#")) {
                continue;
            }

            String[] tokens = line.split("\\s+");  //разделяем по люьому количеству пробелов

            try {
                Command command = factory.create(tokens[0]);
                command.execute(tokens, context);
            } catch (CalculatorExeption e) {
                System.err.println("Ошибка: " + e.getMessage());
            } catch (Exception e) {
                System.err.println("Неизвестная ошибка: " + e.getMessage());
            }
        }
    }
}