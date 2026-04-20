package org.example;

import org.example.commands.Command;
import org.example.exceptions.InsufficientStackException;
import org.example.exceptions.InvalidArgumentsCountException;
import org.example.exceptions.InvalidArgumentsException;


/**
 * Команда ищет минимальный элемент среди n верхних элементов стека
 */
@CommandName("MIN")
public class MinCommand implements Command {
    
    @Override
    public void execute(String[] args, Context context) {
        if (args.length != 2){
            throw new InvalidArgumentsCountException(args[0], 1, args.length - 1);
        }

        String command = args[0];
        String param = args[1];
        int n;

        try{
            n = Integer.parseInt(param);
        } catch(NumberFormatException e){
            throw new InvalidArgumentsException(command, param, "Параметр должен быть натуральным числом!");
        }
        if (n < 1){
            throw new InvalidArgumentsException(command, param, "Параметр n должен положительным!");
        }
        if (context.getStackSize() < n){
            throw new InsufficientStackException(n, context.getStackSize());
        }
        double min =  Double.MAX_VALUE;
        
        for (int i = 0; i < n; ++i){
            double tmp = context.peek();
            if (tmp < min){
                min = tmp;
            }
        }
        System.out.println("СУЧАРА");
        context.push(min);
    }
}