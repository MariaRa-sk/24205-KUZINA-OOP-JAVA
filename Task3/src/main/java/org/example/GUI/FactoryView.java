package org.example.GUI;

import org.example.factory.FactoryUpdateEvent;
import org.example.factory.*;
import javax.swing.*;
import java.awt.*;

public class FactoryView extends JFrame implements FactoryListener {
    private final Factory factory;

    private JLabel bodyStorageLabel;
    private JLabel motorStorageLabel;
    private JLabel accStorageLabel;
    private JLabel carStorageLabel;

    private JLabel bodyProducedLabel;
    private JLabel motorProducedLabel;
    private JLabel accProducedLabel;
    private JLabel carProducedLabel;

    private JButton startButton;
    private JButton stopButton;

    private JSlider bodySlider;
    private JSlider motorSlider;
    private JSlider accSlider;
    private JSlider dealerSlider;
    private JLabel queueLabel;

    public FactoryView(Factory factory) {
        this.factory = factory;
        factory.addListener(this);

        setTitle("Фабрика автомобилей");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(650, 550);
        setLayout(new GridLayout(0, 1, 5, 5));

        add(createStoragePanel());
        add(createProducedPanel());
        add(createSlidersPanel());
        add(createButtonsPanel());
        queueLabel = new JLabel("Задач в очереди: 0");
        add(queueLabel);
        setJMenuBar(createMenuBar());

        setVisible(true);
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu settingsMenu = new JMenu("Настройки");

        JMenuItem lookAndFeelItem = new JMenuItem("Сменить тему");
        lookAndFeelItem.addActionListener(e -> changeLookAndFeel());
        settingsMenu.add(lookAndFeelItem);

        JMenuItem aboutItem = new JMenuItem("О программе");
        aboutItem.addActionListener(e ->
                JOptionPane.showMessageDialog(this, "Фабрика автомобилей v1.0"));
        settingsMenu.add(aboutItem);

        JMenuItem exitItem = new JMenuItem("Выход");
        exitItem.addActionListener(e -> System.exit(0));
        settingsMenu.add(exitItem);

        menuBar.add(settingsMenu);
        return menuBar;
    }

    private void changeLookAndFeel() {
        try {
            String current = UIManager.getLookAndFeel().getClass().getName();
            if (current.contains("Metal")) {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } else {
                UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            }
            SwingUtilities.updateComponentTreeUI(this);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private JPanel createStoragePanel() {
        JPanel panel = new JPanel(new GridLayout(4, 2));
        panel.setBorder(BorderFactory.createTitledBorder("Склады"));

        panel.add(new JLabel("Кузова:"));
        bodyStorageLabel = new JLabel("0 / " + factory.getStorageBody().getCapacity());
        panel.add(bodyStorageLabel);

        panel.add(new JLabel("Моторы:"));
        motorStorageLabel = new JLabel("0 / " + factory.getStorageMotor().getCapacity());
        panel.add(motorStorageLabel);

        panel.add(new JLabel("Аксессуары:"));
        accStorageLabel = new JLabel("0 / " + factory.getStorageAccessory().getCapacity());
        panel.add(accStorageLabel);

        panel.add(new JLabel("Машины:"));
        carStorageLabel = new JLabel("0 / " + factory.getStorageCar().getCapacity());
        panel.add(carStorageLabel);

        return panel;
    }

    private JPanel createProducedPanel() {
        JPanel panel = new JPanel(new GridLayout(2, 4));
        panel.setBorder(BorderFactory.createTitledBorder("Произведено"));

        panel.add(new JLabel("Кузовов:"));
        bodyProducedLabel = new JLabel("0");
        panel.add(bodyProducedLabel);

        panel.add(new JLabel("Моторов:"));
        motorProducedLabel = new JLabel("0");
        panel.add(motorProducedLabel);


        panel.add(new JLabel("Аксессуаров:"));
        accProducedLabel = new JLabel("0");
        panel.add(accProducedLabel);

        panel.add(new JLabel("Машин:"));
        carProducedLabel = new JLabel("0");
        panel.add(carProducedLabel);

        return panel;
    }

    private JPanel createSlidersPanel() {
        JPanel panel = new JPanel(new GridLayout(4, 3));
        panel.setBorder(BorderFactory.createTitledBorder("Задержка (мс)"));

        JLabel bodySliderValue = new JLabel("1000");
        panel.add(new JLabel("Поставщики кузовов:"));
        bodySlider = new JSlider(100, 5000, 1000);
        bodySlider.addChangeListener(e -> {
            int delay = bodySlider.getValue();
            bodySliderValue.setText(String.valueOf(delay));
        });
        panel.add(bodySlider);
        panel.add(bodySliderValue);

        JLabel motorSliderValue = new JLabel("1000");
        panel.add(new JLabel("Поставщики моторов:"));
        motorSlider = new JSlider(100, 5000, 1000);
        motorSlider.addChangeListener(e -> {
            int delay = motorSlider.getValue();
            motorSliderValue.setText(String.valueOf(delay));
        });
        panel.add(motorSlider);
        panel.add(motorSliderValue);

        JLabel accSliderValue = new JLabel("1000");
        panel.add(new JLabel("Поставщики аксессуаров:"));
        accSlider = new JSlider(100, 5000, 1000);
        accSlider.addChangeListener(e -> {
            int delay = accSlider.getValue();
            accSliderValue.setText(String.valueOf(delay));
        });
        panel.add(accSlider);
        panel.add(accSliderValue);

        JLabel dealerSliderValue = new JLabel("2000");
        panel.add(new JLabel("Дилеры:"));
        dealerSlider = new JSlider(100, 5000, 2000);
        dealerSlider.addChangeListener(e -> {
            int delay = dealerSlider.getValue();
            dealerSliderValue.setText(String.valueOf(delay));
        });
        panel.add(dealerSlider);
        panel.add(dealerSliderValue);

        return panel;
    }

    private JPanel createButtonsPanel() {
        JPanel panel = new JPanel();

        startButton = new JButton("Старт");
        stopButton = new JButton("Стоп");
        stopButton.setEnabled(false);

        panel.add(startButton);
        panel.add(stopButton);

        return panel;
    }

    @Override
    public void onFactoryUpdate(FactoryUpdateEvent event) {
        SwingUtilities.invokeLater(() -> {
            bodyStorageLabel.setText(event.getBodyStorageSize() + " / " + event.getBodyStorageCapacity());
            motorStorageLabel.setText(event.getMotorStorageSize() + " / " + event.getMotorStorageCapacity());
            accStorageLabel.setText(event.getAccStorageSize() + " / " + event.getAccStorageCapacity());
            carStorageLabel.setText(event.getCarStorageSize() + " / " + event.getCarStorageCapacity());
            bodyProducedLabel.setText(String.valueOf(event.getBodyProduced()));
            motorProducedLabel.setText(String.valueOf(event.getMotorProduced()));
            accProducedLabel.setText(String.valueOf(event.getAccProduced()));
            carProducedLabel.setText(String.valueOf(event.getCarProduced()));
            queueLabel.setText("Задач в очереди: " + event.getQueueSize());
        });
    }

    public JButton getStartButton() { return startButton; }
    public JButton getStopButton() { return stopButton; }
    public JSlider getBodySlider() { return bodySlider; }
    public JSlider getMotorSlider() { return motorSlider; }
    public JSlider getAccSlider() { return accSlider; }
    public JSlider getDealerSlider() { return dealerSlider; }
}
