package org.example.commands;

import org.example.CommandName;
import org.example.Context;
import org.example.exceptions.InsufficientStackException;
import org.example.exceptions.InvalidArgumentsCountException;

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
        if (args.length > 1){
            throw new InvalidArgumentsCountException(args[0], 0, args.length - 1);
        }
        System.out.println("Верхний элемент стека: " + context.peek());
    }
}
