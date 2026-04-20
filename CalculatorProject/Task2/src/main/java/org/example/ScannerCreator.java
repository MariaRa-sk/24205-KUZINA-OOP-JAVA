package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Определяет источник ввода для сканера.
 * Позволяет читать команды из файла или консоли.
 */
public class ScannerCreator {
    public Scanner createScanner(String[] args) throws FileNotFoundException {
        if (args.length > 0) {
            return createFileScanner(args[0]);
        } else {
            return createConsoleScanner();
        }
    }

    public Scanner createFileScanner(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new FileNotFoundException("Файл не найден: " + filePath);
        }
        if (!file.canRead()) {
            throw new FileNotFoundException("Нет прав на чтение файла: " + filePath);
        }
        return new Scanner(file);
    }

    public Scanner createConsoleScanner() {
        return new Scanner(System.in);
    }
}