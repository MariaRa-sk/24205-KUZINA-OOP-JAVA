package org.example;

import org.example.commands.DefineCommand;
import org.example.exceptions.InvalidArgumentsException;
import org.example.exceptions.InvalidArgumentsCountException;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class DefineCommandTest {
    private Context context;
    private DefineCommand cmd;

    @BeforeEach
    void setUp() {
        context = new Context();
        cmd = new DefineCommand();
    }

    @Test
    void simpleDefine() {
        String[] args = {"DEFINE", "x", "42"};
        cmd.execute(args, context);
        double value = context.getNumber("x");
        assertEquals(42.0, value);
    }

    @Test
    void DefineNegativeNumber() {
        String[] args = {"DEFINE", "x", "-42"};
        cmd.execute(args, context);
        double value = context.getNumber("x");
        assertEquals(-42.0, value);
    }

    @Test
    void DefineDoubleNumber() {
        String[] args = {"DEFINE", "x", "42.37"};
        cmd.execute(args, context);
        double value = context.getNumber("x");
        assertEquals(42.37, value, 0.0001);
    }

    @Test
    void DefineNoArgs() {
        String[] args = {"DEFINE"};

        assertThrows(InvalidArgumentsCountException.class, () -> {
            cmd.execute(args, context);
        });
    }

    @Test
    void DefineMoreArgs() {
        String[] args = {"DEFINE", "a", "42", "52"};
        assertThrows(InvalidArgumentsCountException.class, () -> {
            cmd.execute(args, context);
        });
    }

    @Test
    void DefineWrongArgsTwoNumbers() {
        String[] args = {"DEFINE", "52", "42"};
        assertThrows(InvalidArgumentsException.class, () -> {
            cmd.execute(args, context);
        });
    }

    @Test
    void DefineWrongArgsTwoParameters() {
        String[] args = {"DEFINE", "a", "b"};
        assertThrows(InvalidArgumentsException.class, () -> {
            cmd.execute(args, context);
        });
    }






      
}
