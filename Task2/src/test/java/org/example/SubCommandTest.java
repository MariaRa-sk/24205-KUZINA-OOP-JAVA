package org.example;

import org.example.commands.SubCommand;
import org.example.exceptions.InsufficientStackException;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class SubCommandTest {
    private Context context;
    private SubCommand cmd;

    @BeforeEach
    void setUp() {
        context = new Context();
        cmd = new SubCommand();
    }

    @Test
    public void simpleSub() {
        context.push(54);
        context.push(2);
        cmd.execute(new String[]{ "-" }, context);
        assertEquals(52.0, context.peek());
    }

    @Test
    public void subWithZero() {
        context.push(67);
        context.push(0);
        cmd.execute(new String[]{ "-" }, context);
        assertEquals(67.0, context.peek());
    }

    @Test
    public void subDouble(){
        context.push(3.5);
        context.push(1.2);
        cmd.execute(new String[]{ "-" }, context);
        assertEquals(2.3, context.peek(), 0.0001);
    }

    @Test
    public void subPositiveAndNegative(){
        context.push(100);
        context.push(-55.5);
        cmd.execute(new String[]{"-"}, context);
        assertEquals(155.5, context.peek(), 0.0001);
    }

    @Test
    public void subWithEmptyStack() {
        assertThrows(InsufficientStackException.class, () -> {
            cmd.execute(new String[]{"-"}, context);
        });
    }

    @Test
    public void subOneValue(){
        context.push(10);
        assertThrows(InsufficientStackException.class, () -> {
            cmd.execute(new String[]{"-"}, context);
        });
    }
}
