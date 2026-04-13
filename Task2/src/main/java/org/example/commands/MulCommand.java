package org.example.commands;

import org.example.CommandName;
import org.example.Context;
import org.example.exceptions.InsufficientStackException;

@CommandName("*")
public class MulCommand implements Command{
    @Override
    public void execute(String[] args, Context context){
        if (context.getStackSize() < 2){
            throw new InsufficientStackException(2, context.getStackSize());
        }
        double num1 = context.pop();
        double num2 = context.pop();
        context.push(num1 * num2);
    }
}
