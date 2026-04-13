package org.example.commands;

import org.example.CommandName;
import org.example.Context;
import org.example.exceptions.InsufficientStackException;
import org.example.exceptions.NegativeSqrtException;

@CommandName("SQRT")
public class SqrtCommand implements Command{
    @Override
    public void execute (String[] args, Context context){
        if (context.getStackSize() < 1){
            throw new InsufficientStackException(1, context.getStackSize());
        }
        double num = context.pop();
        if (num < 0){
            context.push(num);
            throw new NegativeSqrtException();
        }
        context.push(Math.sqrt(num));
    }
}
