package org.example.commands;

import org.example.CommandName;
import org.example.Context;
import org.example.exceptions.InvalidArgumentsCountException;
import org.example.exceptions.InvalidArgumentsException;

@CommandName("PUSH")
public class PushCommand implements Command{
    @Override
    public void execute(String [] args, Context context){
        if (args.length != 2){
            throw new InvalidArgumentsCountException(args[0], 1, args.length - 1);
        }
        String command = args[0];
        String param = args[1];

        try{
            double value = Double.parseDouble(param);
            context.push(value);
        } catch(NumberFormatException e){
            if (context.hasParameter(param)){
                double value = context.getNumber(param);
                context.push(value);
            }
            else{
                throw new InvalidArgumentsException(command, param, "Нет числа по такому аргументу");
            }
        }
    }
}
