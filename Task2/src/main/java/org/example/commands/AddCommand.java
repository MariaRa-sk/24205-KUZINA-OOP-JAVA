package org.example.commands;

import org.example.CommandName;
import org.example.Context;
import org.example.exceptions.InsufficientStackException;

/**
 * Команда сложения двух верхних чисел стека.
 * Извлекает два числа из стека и помещает результат их сложения.
 */
@CommandName("+")
public class AddCommand implements Command {
    @Override
    public void execute(String[] args, Context context){
        if (context.getStackSize() < 2){
            throw new InsufficientStackException(2, context.getStackSize());
        }
        double num1 = context.pop();
        double num2 = context.pop();
        context.push(num1 + num2);
    }
}
