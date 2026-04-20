package org.example;

import org.example.commands.DefineCommand;
import org.example.exceptions.InsufficientStackException;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class DefineCommandTest {
    private Context context;
    private DefineCommand cmd;

    @BeforeEach
    void setUp() {
        context = new Context();
        cmd = new DefineCommand();
    }

      
}
