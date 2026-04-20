package org.example;

import org.example.commands.PrintCommand;
import org.example.commands.PushCommand;
import org.example.exceptions.InsufficientStackException;
import org.example.exceptions.InvalidArgumentsCountException;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class PrintCommandTest {
    private Context context;
    private PrintCommand cmd;
    private PushCommand pushCmd;
    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        context = new Context();
        cmd = new PrintCommand();
        pushCmd = new PushCommand();
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
        outContent.reset();
    }

    @Test
    void simplePrint() {
        pushCmd.execute(new String[]{"PUSH", "42"}, context);
        cmd.execute(new String[]{"PRINT"}, context);
        assertTrue(outContent.toString().contains("42"));
    }

    @Test
    void PrintEmptyStack() {
        assertThrows(InsufficientStackException.class, () -> {
            cmd.execute(new String[]{"PRINT"}, context);
        });
    }

    @Test
    void PrintWithArgs() {
        pushCmd.execute(new String[]{"PUSH", "100"}, context);

        assertThrows(InvalidArgumentsCountException.class, () -> {
            cmd.execute(new String[]{"PRINT", "extra"}, context);
        });
    }

    @Test
    void PrintDoesNotRemoveElement() {
        pushCmd.execute(new String[]{"PUSH", "77"}, context);
        cmd.execute(new String[]{"PRINT"}, context);
        assertEquals(1, context.getStackSize());
        assertEquals(77.0, context.peek());
    }
}