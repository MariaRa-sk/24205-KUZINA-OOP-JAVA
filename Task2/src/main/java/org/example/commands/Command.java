package org.example.commands;

import org.example.Context;

/**
 * Интерфейс для всех команд калькулятора.
 */
public interface Command {
    void execute(String[] args, Context context);
}
