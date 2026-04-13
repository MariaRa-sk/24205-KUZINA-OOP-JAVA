package org.example.commands;

import org.example.Context;

public interface Command {
    void execute(String[] args, Context context);
}
