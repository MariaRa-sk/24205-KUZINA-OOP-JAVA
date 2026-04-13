package org.example;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * Контекст выполнения команд.
 * Хранит стек чисел и словарь именованных параметров.
 */
public class Context {
    private Deque<Double> stack = new ArrayDeque<>();
    private Map<String, Double> parametrs = new HashMap<>();

    public int getStackSize() {
        return stack.size();
    }

    public void push(double value){
        stack.push(value);
    }

    public double pop(){
        if (stack.isEmpty()) {
            throw new RuntimeException("Стек пуст! Нельзя извлечь элемент");
        }
        return stack.pop();
    }

    public double peek() {
        if (stack.isEmpty()) {
            throw new RuntimeException("Стек пуст! Нельзя посмотреть верхний элемент");
        }
        return stack.peek();
    }

    public boolean hasParameter(String name){
        return parametrs.containsKey(name);
    }

    public double getNumber(String name) {
        if (!parametrs.containsKey(name)) {
            throw new RuntimeException("Параметр не найден: " + name);
        }
        return parametrs.get(name);
    }

    // кладем в map
    public void put(String key, double value){
        parametrs.put(key, value);
    }

}




