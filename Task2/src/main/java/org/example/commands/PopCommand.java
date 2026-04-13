package org.example.commands;

import org.example.CommandName;
import org.example.Context;
import org.example.exceptions.InsufficientStackException;

/**
 * Команда удаления верхнего элемента стека.
 */
@CommandName("POP")
public class PopCommand implements Command{
    @Override
    public void execute(String [] args, Context context){
        if (context.getStackSize() < 1){
            throw new InsufficientStackException(1, context.getStackSize());
        }
        context.pop();
    }
}
