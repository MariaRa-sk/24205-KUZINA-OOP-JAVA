package org.example;

import org.example.commands.*;
import org.example.exceptions.CommandFactoryException;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    private Map<String, Class<? extends Command>> commands = new HashMap<>();

    public CommandFactory() {
        loadCommands();
    }

    private void loadCommands() {
        try {
            InputStream is = getClass().getResourceAsStream("/commands.config");
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));

            String line;
            while ((line = reader.readLine()) != null) {
                Class<?> clazz = Class.forName(line);

                if (clazz.isAnnotationPresent(CommandName.class)) {
                    CommandName annotation = clazz.getAnnotation(CommandName.class);
                    commands.put(annotation.value(), (Class<? extends Command>) clazz);
                }
            }

        } catch (Exception e) {
            throw new RuntimeException("Ошибка загрузки команд", e);
        }
    }

    public Command create(String name) {
        Class<? extends Command> clazz = commands.get(name);
        if (clazz == null) {
            throw new CommandFactoryException("Неизвестная команда: " + name);
        }

        try {
            return clazz.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Ошибка создания команды", e);
        }
    }
}