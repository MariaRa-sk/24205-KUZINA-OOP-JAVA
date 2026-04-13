package org.example.commands;

import org.example.CommandName;
import org.example.Context;
import org.example.exceptions.InsufficientStackException;

/**
 * Команда печати верхнего элемента стека на экран.
 */
@CommandName("PRINT")
public class PrintCommand implements Command{
    @Override
    public void execute(String[] args, Context context){
        if (context.getStackSize() < 1){
            throw new InsufficientStackException(1, context.getStackSize());
        }
        System.out.println("Верхний элемент стека: " + context.peek());
    }
}
