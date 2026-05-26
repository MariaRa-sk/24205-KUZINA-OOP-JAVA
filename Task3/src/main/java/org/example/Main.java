package org.example;

import org.example.factory.Config;
import org.example.factory.Factory;
import org.example.GUI.FactoryView;
import org.example.GUI.FactoryController;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}

        Config config = new Config("src/main/resources/config.properties");
        Factory factory = new Factory(config);
        FactoryView view = new FactoryView(factory);
        new FactoryController(factory, view);
    }
}
