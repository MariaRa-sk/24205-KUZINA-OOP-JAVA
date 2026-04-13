package org.example.commands;

import org.example.CommandName;
import org.example.Context;
import org.example.exceptions.InvalidArgumentsException;
import org.example.exceptions.InvalidArgumentsCountException;

/**
 * Команда определения переменной.
 * Сохраняет числовое значение по указанному имени переменной.
 */
@CommandName("DEFINE")
public class DefineCommand implements Command{
    @Override
    public void execute(String[] args, Context context){
        if (args.length != 3){
            throw new InvalidArgumentsCountException(args[0], 3, args.length - 1);
        }

        String command = args[0];
        String paramName = args[1];
        String valueStr = args[2];

        try{
            Double.parseDouble(paramName);
            throw new InvalidArgumentsException(command, paramName, "Имя параметра не может быть числом!");
        } catch(NumberFormatException e){

        }

        try{
            double value = Double.parseDouble(valueStr);
            context.put(paramName, value);
        } catch(NumberFormatException e){
            throw new InvalidArgumentsException(command, valueStr, "Параметр должен быть числом!");
        }
    }
}
