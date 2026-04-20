package org.example;

import org.example.commands.DefineCommand;
import org.example.commands.PopCommand;
import org.example.commands.PushCommand;
import org.example.exceptions.InsufficientStackException;
import org.example.exceptions.InvalidArgumentsCountException;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class PopCommandTest {
    private Context context;
    private PopCommand cmd;
    private PushCommand pushCmd;

    @BeforeEach
    void setUp() {
        context = new Context();
        cmd = new PopCommand();
        pushCmd = new PushCommand();
    }

    @Test
    void simplePop() {
        pushCmd.execute(new String[]{"PUSH", "42"}, context);
        assertEquals(1, context.getStackSize());

        cmd.execute(new String[]{"POP"}, context);

        assertEquals(0, context.getStackSize());
    }

    @Test
    void PopWithoutArgs() {
        pushCmd.execute(new String[]{"PUSH", "42"}, context);

        assertDoesNotThrow(() -> {
            cmd.execute(new String[]{"POP"}, context);
        });
        assertEquals(0, context.getStackSize());
    }

    @Test
    void PopMultipleElements() {
        pushCmd.execute(new String[]{"PUSH", "10"}, context);
        pushCmd.execute(new String[]{"PUSH", "20"}, context);
        pushCmd.execute(new String[]{"PUSH", "30"}, context);
        assertEquals(3, context.getStackSize());

        cmd.execute(new String[]{"POP"}, context);
        assertEquals(2, context.getStackSize());

        cmd.execute(new String[]{"POP"}, context);
        assertEquals(1, context.getStackSize());

        double value = context.pop();
        assertEquals(10.0, value);
    }

    @Test
    void PopEmptyStack() {
        assertEquals(0, context.getStackSize());

        assertThrows(InsufficientStackException.class, () -> {
            cmd.execute(new String[]{"POP"}, context);
        });
    }

    @Test
    void PopWithExtraArgs() {
        pushCmd.execute(new String[]{"PUSH", "42"}, context);

        String[] args = {"POP", "extra"};

        assertThrows(InvalidArgumentsCountException.class, () -> {
            cmd.execute(args, context);
        });
    }

    @Test
    void PopWithManyExtraArgs() {
        pushCmd.execute(new String[]{"PUSH", "42"}, context);

        String[] args = {"POP", "extra1", "extra2"};

        assertThrows(InvalidArgumentsCountException.class, () -> {
            cmd.execute(args, context);
        });
    }

    @Test
    void PopAfterPop() {
        pushCmd.execute(new String[]{"PUSH", "100"}, context);
        cmd.execute(new String[]{"POP"}, context);

        assertThrows(InsufficientStackException.class, () -> {
            cmd.execute(new String[]{"POP"}, context);
        });
    }


    @Test
    void PopWithVariable() {
        DefineCommand def = new DefineCommand();
        def.execute(new String[]{"DEFINE", "x", "55"}, context);
        pushCmd.execute(new String[]{"PUSH", "x"}, context);
        assertEquals(1, context.getStackSize());
        cmd.execute(new String[]{"POP"}, context);
        assertEquals(0, context.getStackSize());
    }
}