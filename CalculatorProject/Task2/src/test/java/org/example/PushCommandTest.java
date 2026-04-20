package org.example;

import org.example.commands.DefineCommand;
import org.example.commands.PushCommand;
import org.example.exceptions.InvalidArgumentsException;
import org.example.exceptions.InvalidArgumentsCountException;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class PushCommandTest {
    private Context context;
    private PushCommand cmd;

    @BeforeEach
    void setUp() {
        context = new Context();
        cmd = new PushCommand();
    }

    @Test
    void simplePush() {
        String[] args = {"PUSH", "42"};
        cmd.execute(args, context);
        double value = context.pop();
        assertEquals(42.0, value);
    }

    @Test
    void PushNegativeNumber() {
        String[] args = {"PUSH", "-42"};
        cmd.execute(args, context);
        double value = context.pop();
        assertEquals(-42.0, value);
    }

    @Test
    void PushDoubleNumber() {
        String[] args = {"PUSH", "42.37"};
        cmd.execute(args, context);
        double value = context.pop();
        assertEquals(42.37, value, 0.0001);
    }

    @Test
    void PushZero() {
        String[] args = {"PUSH", "0"};
        cmd.execute(args, context);

        double value = context.pop();
        assertEquals(0.0, value);
    }

    @Test
    void PushFromVariable() {
        String[] defineArgs = {"DEFINE", "x", "99"};
        DefineCommand def = new DefineCommand();
        def.execute(defineArgs, context);

        String[] pushArgs = {"PUSH", "x"};
        cmd.execute(pushArgs, context);

        double value = context.pop();
        assertEquals(99.0, value);
    }

    @Test
    void PushMultipleValues() {
        cmd.execute(new String[]{"PUSH", "10"}, context);
        cmd.execute(new String[]{"PUSH", "20"}, context);
        cmd.execute(new String[]{"PUSH", "30"}, context);

        assertEquals(30.0, context.pop());
        assertEquals(20.0, context.pop());
        assertEquals(10.0, context.pop());
    }

    @Test
    void PushNoArgs() {
        String[] args = {"PUSH"};

        assertThrows(InvalidArgumentsCountException.class, () -> {
            cmd.execute(args, context);
        });
    }

    @Test
    void PushMoreArgs() {
        String[] args = {"PUSH", "42", "extra"};

        assertThrows(InvalidArgumentsCountException.class, () -> {
            cmd.execute(args, context);
        });
    }

    @Test
    void PushUnknownVariable() {
        String[] args = {"PUSH", "undefinedVar"};

        assertThrows(InvalidArgumentsException.class, () -> {
            cmd.execute(args, context);
        });
    }



}