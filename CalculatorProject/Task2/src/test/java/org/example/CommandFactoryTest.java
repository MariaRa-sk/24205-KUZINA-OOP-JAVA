package org.example;

import org.example.exceptions.CommandFactoryException;
import org.example.commands.Command;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class CommandFactoryTest {
    private CommandFactory factory;

    @BeforeEach
    void setUp() {
        factory = new CommandFactory();
    }

    @Test
    @Disabled("Требует собранных jar файлов")
    void createExistingCommand() {
        Command command = factory.create("PUSH");
        assertNotNull(command);
    }

    @Test
    void createUnknownCommand() {
        assertThrows(CommandFactoryException.class, () -> {
            factory.create("UNKNOWN");
        });
    }

    @Test
    void createNullCommand() {
        assertThrows(CommandFactoryException.class, () -> {
            factory.create(null);
        });
    }

    @Test
    @Disabled("Требует собранных jar файлов")
    void createAllBuiltinCommands() {
        String[] commands = {"PUSH", "POP", "PRINT", "DEFINE"};

        for (String cmdName : commands) {
            assertDoesNotThrow(() -> {
                factory.create(cmdName);
            });
        }
    }

}
