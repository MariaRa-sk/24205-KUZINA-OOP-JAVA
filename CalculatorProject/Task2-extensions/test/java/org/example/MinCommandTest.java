package org.example;

import org.example.commands.PushCommand;
import org.example.exceptions.InsufficientStackException;
import org.example.exceptions.InvalidArgumentsCountException;
import org.example.exceptions.InvalidArgumentsException;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class MinCommandTest {
    private Context context;
    private MinCommand cmd;
    private PushCommand pushCmd;

    @BeforeEach
    void setUp() {
        context = new Context();
        cmd = new MinCommand();
        pushCmd = new PushCommand();
    }

    @Test
    void simpleMin() {
        pushCmd.execute(new String[]{"PUSH", "10"}, context);
        pushCmd.execute(new String[]{"PUSH", "5"}, context);
        pushCmd.execute(new String[]{"PUSH", "8"}, context);
        cmd.execute(new String[]{"MIN", "3"}, context);
        assertEquals(5.0, context.pop());
    }

    @Test
    void MinWithOneElement() {
        pushCmd.execute(new String[]{"PUSH", "42"}, context);
        cmd.execute(new String[]{"MIN", "1"}, context);
        assertEquals(42.0, context.pop());
    }

    @Test
    void MinNoArgs() {
        assertThrows(InvalidArgumentsCountException.class, () -> {
            cmd.execute(new String[]{"MIN"}, context);
        });
    }

    @Test
    void MinWithInvalidNumber() {
        pushCmd.execute(new String[]{"PUSH", "10"}, context);
        assertThrows(InvalidArgumentsException.class, () -> {
            cmd.execute(new String[]{"MIN", "abc"}, context);
        });
    }

    @Test
    void MinWithNegativeN() {
        pushCmd.execute(new String[]{"PUSH", "10"}, context);
        assertThrows(InvalidArgumentsException.class, () -> {
            cmd.execute(new String[]{"MIN", "-3"}, context);
        });
    }

    @Test
    void MinEmptyStack() {
        assertThrows(InsufficientStackException.class, () -> {
            cmd.execute(new String[]{"MIN", "1"}, context);
        });
    }

    @Test
    void MinInsufficientStackSize() {
        pushCmd.execute(new String[]{"PUSH", "10"}, context);
        assertThrows(InsufficientStackException.class, () -> {
            cmd.execute(new String[]{"MIN", "3"}, context);
        });
    }
}