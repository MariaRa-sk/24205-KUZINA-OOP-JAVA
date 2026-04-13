package org.example;

import org.example.commands.DivCommand;
import org.example.exceptions.DivisionByZeroException;
import org.example.exceptions.InsufficientStackException;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;


public class DivCommandTest {
    private Context context;
    private DivCommand cmd;

    @BeforeEach
    void setUp() {
        context = new Context();
        cmd = new DivCommand();
    }

    @Test
    public void simpleDiv(){
        context.push(10);
        context.push(5);
        cmd.execute(new String[]{":"}, context);
        assertEquals(2, context.peek());
    }

    @Test
    public void zeroInNumerator(){ //ноль в числителе
        context.push(0);
        context.push(5);
        cmd.execute(new String[]{":"}, context);
        assertEquals(0, context.peek());
    }

    @Test
    public void zeroInDenominator(){ //ноль в знаменателе
        context.push(5);
        context.push(0);
        assertThrows(DivisionByZeroException.class, () -> {
            cmd.execute(new String[]{":"}, context);
        });
    }

    @Test
    public void divDouble(){
        context.push(20.4);
        context.push(3.5);
        cmd.execute(new String[]{":"}, context);
        assertEquals(5.82857142857, context.peek(), 0.0001);
    }

    @Test
    public void divEmptyStack(){
        assertThrows(InsufficientStackException.class, () -> {
            cmd.execute(new String[]{":"}, context);
        });
    }
}
