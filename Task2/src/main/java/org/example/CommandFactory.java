package org.example;

import org.example.commands.Command;
import org.example.exceptions.CommandFactoryException;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.JarFile;
import java.util.jar.JarEntry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Фабрика команд.
 * Загружает команды из JAR-файлов, указанных в конфигурационном файле.
 */
public class CommandFactory {
    private final Logger log = LoggerFactory.getLogger(CommandFactory.class);
    private Map<String, Class<? extends Command>> commands = new HashMap<>();

    /**
     * Создаёт фабрику и загружает все доступные команды.
     */
    public CommandFactory() {
        log.info("Инициализация CommandFactory");
        loadCommands();
        log.info("Загружено команд: {}", commands.size());
    }

    /**
     * Загружает команды из конфигурационного файла.
     * Читает пути к JAR-файлам из /commands.config и загружает команды из них.
     */
    private void loadCommands() {
        try {
            InputStream is = getClass().getResourceAsStream("/commands.config");
            if (is == null) {
                throw new RuntimeException("Не найден файл конфигурации commands.config");
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line;

            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty() || line.startsWith("#")) {
                    continue;
                }
                loadCommandsFromJar(line);
            }

        } catch (Exception e) {
            throw new RuntimeException("Ошибка загрузки команд", e);
        }
    }

    /**
     * Загружает команды из указанного JAR-файла.
     * Сканирует JAR, находит классы с аннотацией {@CommandName}
     * и регистрирует их в фабрике.
     */
    private void loadCommandsFromJar(String jarPath) {
        log.debug("Загрузка JAR-файла: {}", jarPath);
        try {
            JarFile jarFile = new JarFile(jarPath);
            URL jarUrl = new URL("file:" + jarPath);
            URLClassLoader classLoader = new URLClassLoader(new URL[]{jarUrl});

            java.util.Enumeration<JarEntry> entries = jarFile.entries();

            while (entries.hasMoreElements()) {
                JarEntry entry = entries.nextElement();
                String entryName = entry.getName();

                if (entryName.endsWith(".class")) {
                    String className = entryName
                            .replace("/", ".")
                            .replace(".class", "");

                    try {
                        Class<?> clazz = classLoader.loadClass(className);

                        if (clazz.isAnnotationPresent(CommandName.class) &&
                                Command.class.isAssignableFrom(clazz)) {
                            log.debug("Найден класс команды: {}", className);
                            CommandName annotation = clazz.getAnnotation(CommandName.class);
                            String commandName = annotation.value();

                            commands.put(commandName, (Class<? extends Command>) clazz);
                        }
                    } catch (ClassNotFoundException e) {
                        log.error("Ошибка загрузки JAR: {}", jarPath, e);
                    }
                }
            }

            jarFile.close();

        } catch (Exception e) {
            System.err.println("Ошибка загрузки JAR-файла: " + jarPath);
        }
    }

    /**
     * Создаёт экземпляр команды по её имени.
     * @param name имя команды (значение из аннотации {@link CommandName})
     * @return экземпляр команды
     */
    public Command create(String name) {
        log.debug("Создание команды: {}", name);
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