package org.example;


import org.example.commands.MulCommand;
import org.example.exceptions.InsufficientStackException;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class MulCommandTest {
    private Context context;
    private MulCommand cmd;

    @BeforeEach
    void setUp() {
        context = new Context();
        cmd = new MulCommand();
    }

    @Test
    public void simpleMulTest(){
        context.push(10);
        context.push(10);
        cmd.execute(new String[] {"*"}, context);
        assertEquals(100, context.peek());
    }

    @Test
    public void mulWithZero(){
        context.push(50);
        context.push(0);
        cmd.execute(new String[] {"*"}, context);
        assertEquals(0, context.peek());
    }

    @Test
    public void mulDouble(){
        context.push(14.5);
        context.push(15.95);
        cmd.execute(new String[] {"*"}, context);
        assertEquals(231.275, context.peek(), 0.0001);
    }

    @Test
    public void mulTwoNegative(){
        context.push(-50);
        context.push(-40.5);
        cmd.execute(new String[] {"*"}, context);
        assertEquals(2025, context.peek(),0.0001);
    }

    @Test
    public void mulDifferentSigns(){
        context.push(-3.3);
        context.push(4.7);
        cmd.execute(new String[] {"*"}, context);
        assertEquals(-15.51, context.peek(),0.0001);
    }

    @Test
    public void mulEmptyStack(){
        assertThrows(InsufficientStackException.class, () -> {
            cmd.execute(new String[]{"*"}, context);
        });
    }
}
//для исключение сос теком отдельный класс тестирования?