package org.example.factory;

import org.example.factory.details.Accessory;
import org.example.factory.details.Body;
import org.example.factory.details.Car;
import org.example.factory.details.Motor;
import org.example.factory.people.Dealer;
import org.example.factory.people.Supplier;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Factory {
    private final Config config;

    private final Storage<Body> storageBody;
    private final Storage<Motor> storageMotor;
    private final Storage<Accessory> storageAccessory;
    private final Storage<Car> storageCar;

    private final ThreadPoolExecutor workersPool;
    private final Object controllerMonitor;

    private final StorageController controller;
    private final List<Thread> threads;

    private final List<FactoryListener> listeners = new ArrayList<>();

    private final List<Supplier<Body>> bodySuppliers;
    private final List<Supplier<Motor>> motorSuppliers;
    private final List<Supplier<Accessory>> accSuppliers;
    private final List<Dealer> dealers;

    public Factory(Config config) {
        this.config = config;

        this.storageBody = new Storage<>(config.getStorageBodySize());
        this.storageMotor = new Storage<>(config.getStorageMotorSize());
        this.storageAccessory = new Storage<>(config.getStorageAccessorySize());
        this.storageCar = new Storage<>(config.getStorageAutoSize());
        this.controllerMonitor = new Object();
        this.workersPool = new ThreadPoolExecutor(
                config.getWorkers(), config.getWorkers(),
                0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>()
        );
        this.controller = new StorageController(storageCar, storageBody, storageMotor,
                storageAccessory, workersPool, controllerMonitor,this);


        this.bodySuppliers = new ArrayList<>();
        this.motorSuppliers = new ArrayList<>();
        this.accSuppliers = new ArrayList<>();
        this.dealers = new ArrayList<>();

        this.threads = new ArrayList<>();
    }

    public void start() {

        Thread controllerThread = new Thread(controller);
        controllerThread.start();
        threads.add(controllerThread);


        for (int i = 0; i < config.getBodySuppliers(); i++) {
            Supplier<Body> supplier = new Supplier<>(storageBody, Body.class, this, controller);
            bodySuppliers.add(supplier);  // ← Сохраняем
            Thread t = new Thread(supplier);
            t.start();
            threads.add(t);
        }

        for (int i = 0; i < config.getMotorSuppliers(); i++) {
            Supplier<Motor> supplier = new Supplier<>(storageMotor, Motor.class, this, controller);
            motorSuppliers.add(supplier);
            Thread t = new Thread(supplier);
            t.start();
            threads.add(t);
        }

        for (int i = 0; i < config.getAccessorySuppliers(); i++) {
            Supplier<Accessory> supplier = new Supplier<>(storageAccessory, Accessory.class, this, controller);
            accSuppliers.add(supplier);
            Thread t = new Thread(supplier);
            t.start();
            threads.add(t);
        }

        for (int i = 0; i < config.getDealers(); i++) {
            Dealer dealer = new Dealer(storageCar, controller, this);
            dealers.add(dealer);  // ← Сохраняем
            Thread t = new Thread(dealer);
            t.start();
            threads.add(t);
        }

        new Thread(() -> {
            try {
                Thread.sleep(2000);
                controller.notifyController();
            } catch (InterruptedException ignored) {}
        }).start();
    }

    public void stop() {
        for (Thread t : threads) {
            t.interrupt();
        }
        workersPool.shutdown();
    }

    public void addListener(FactoryListener listener) {
        listeners.add(listener);
    }

    public void notifyListeners() {
        FactoryUpdateEvent event = new FactoryUpdateEvent(this);
        for (FactoryListener listener : listeners) {
            listener.onFactoryUpdate(event);
        }
    }

    public List<Supplier<Body>> getBodySuppliers() { return bodySuppliers; }
    public List<Supplier<Motor>> getMotorSuppliers() { return motorSuppliers; }
    public List<Supplier<Accessory>> getAccessorySuppliers() { return accSuppliers; }
    public List<Dealer> getDealers() { return dealers; }
    public Storage<Body> getStorageBody() { return storageBody; }
    public Storage<Motor> getStorageMotor() { return storageMotor; }
    public Storage<Accessory> getStorageAccessory() { return storageAccessory; }
    public Storage<Car> getStorageCar() { return storageCar; }
    public ThreadPoolExecutor getWorkersPool() { return workersPool; }
    public StorageController getController() { return controller; }
}
