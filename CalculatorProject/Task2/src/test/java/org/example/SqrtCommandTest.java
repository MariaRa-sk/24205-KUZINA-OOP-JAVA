package org.example;

import org.example.commands.SqrtCommand;
import org.example.exceptions.InsufficientStackException;
import org.example.exceptions.NegativeSqrtException;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class SqrtCommandTest {

    private Context context;
    private SqrtCommand cmd;

    @BeforeEach
    void setUp() {
        context = new Context();
        cmd = new SqrtCommand();
    }

    @Test
    public void simpleTest(){
        context.push(144);
        cmd.execute(new String[]{"SQRT"}, context);
        assertEquals(12.0, context.peek());
    }

    @Test
    public void sqrtNegative(){
        context.push(-144);
        assertThrows(NegativeSqrtException.class, () -> {
            cmd.execute(new String[]{"SQRT"}, context);
        });
    }

    @Test
    public void sqrtEmptyStack(){
        assertThrows(InsufficientStackException.class, () -> {
            cmd.execute(new String[]{"SQRT"}, context);
        });
    }

    @Test
    public void sqrtDouble(){
        context.push(76.84);
        cmd.execute(new String[]{"SQRT"}, context);
        assertEquals(8.76584280032, context.peek(), 0.0001);
    }
}
