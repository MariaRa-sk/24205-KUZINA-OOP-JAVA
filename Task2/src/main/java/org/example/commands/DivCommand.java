package org.example.commands;

import org.example.CommandName;
import org.example.Context;
import org.example.exceptions.DivisionByZeroException;
import org.example.exceptions.InsufficientStackException;


/**
 * Команда деления двух верхних чисел стека.
 * Извлекает два числа и помещает результат деления второго на первое.
 */
@CommandName(":")
public class DivCommand implements Command{
    @Override
    public void execute(String[] args, Context context){
        if (context.getStackSize() < 2){
            throw new InsufficientStackException(2, context.getStackSize());
        }
        double num1 = context.pop();
        double num2 = context.pop();
        if (num1 == 0){
            context.push(num2);
            context.push(num1);
            throw new DivisionByZeroException();
        }
        context.push(num2 / num1);
    }
}
