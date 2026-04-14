package org.example;

import org.example.commands.AddCommand;
import org.example.exceptions.InsufficientStackException;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class AddCommandTest {

    private Context context;
    private AddCommand cmd;

    @BeforeEach
    void setUp() {
        context = new Context();
        cmd = new AddCommand();
    }

    @Test
    public void simpleAdd() {
        context.push(50);
        context.push(2);
        //делаем не через фабрику потому что тестируем ADD а не ФАБРИКУ
        cmd.execute(new String[]{ "+" }, context);
        assertEquals(52.0, context.peek());
    }

    @Test
    public void addWithZero() {
        context.push(67);
        context.push(0);
        cmd.execute(new String[]{ "+" }, context);
        assertEquals(67.0, context.peek());
    }

    @Test
    public void addDouble(){
        context.push(2.5);
        context.push(3.7);
        cmd.execute(new String[]{ "+" }, context);
        assertEquals(6.2, context.peek(), 0.0001);
    }

    @Test
    public void addPositiveAndNegative(){
        context.push(100);
        context.push(-55.5);
        cmd.execute(new String[]{"+"}, context);
        assertEquals(44.5, context.peek(), 0.0001);
    }

    @Test
    public void addWithEmptyStack() {
        assertThrows(InsufficientStackException.class, () -> {
            cmd.execute(new String[]{"+"}, context);
        });
    }

    @Test
    public void addOneValue(){
        context.push(10);
        assertThrows(InsufficientStackException.class, () -> {
            cmd.execute(new String[]{"+"}, context);
        });
    }

}


