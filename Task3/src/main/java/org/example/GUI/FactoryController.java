package org.example.GUI;

import org.example.factory.Factory;

public class FactoryController {
    private final Factory factory;
    private final FactoryView view;

    public FactoryController(Factory factory, FactoryView view) {
        this.factory = factory;
        this.view = view;
        init();
    }

    private void init() {
        view.getStartButton().addActionListener(e -> {
            factory.start();
            view.getStartButton().setEnabled(false);
            view.getStopButton().setEnabled(true);
        });

        view.getStopButton().addActionListener(e -> {
            factory.stop();
            view.getStartButton().setEnabled(true);
            view.getStopButton().setEnabled(false);
        });

        view.getBodySlider().addChangeListener(e -> {
            if (!view.getBodySlider().getValueIsAdjusting()) {
                int delay = view.getBodySlider().getValue();
                factory.getBodySuppliers().forEach(s -> s.setDelay(delay));
            }
        });

        view.getMotorSlider().addChangeListener(e -> {
            if (!view.getMotorSlider().getValueIsAdjusting()) {
                int delay = view.getMotorSlider().getValue();
                factory.getMotorSuppliers().forEach(s -> s.setDelay(delay));
            }
        });

        view.getAccSlider().addChangeListener(e -> {
            if (!view.getAccSlider().getValueIsAdjusting()) {
                int delay = view.getAccSlider().getValue();
                factory.getAccessorySuppliers().forEach(s -> s.setDelay(delay));
            }
        });

        view.getDealerSlider().addChangeListener(e -> {
            if (!view.getDealerSlider().getValueIsAdjusting()) {
                int delay = view.getDealerSlider().getValue();
                factory.getDealers().forEach(d -> d.setDelay(delay));
            }
        });
    }
}
