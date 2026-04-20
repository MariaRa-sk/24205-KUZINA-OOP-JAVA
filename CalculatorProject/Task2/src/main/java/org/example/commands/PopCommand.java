package org.example.commands;

import org.example.CommandName;
import org.example.Context;
import org.example.exceptions.InsufficientStackException;
import org.example.exceptions.InvalidArgumentsCountException;

/**
 * Команда удаления верхнего элемента стека.
 */
@CommandName("POP")
public class PopCommand implements Command{
    @Override
    public void execute(String [] args, Context context){
        if (args.length > 1){
            throw new InvalidArgumentsCountException(args[0], 0, args.length - 1);
        }
        if (context.getStackSize() < 1){
            throw new InsufficientStackException(1, context.getStackSize());
        }
        context.pop();
    }
}
