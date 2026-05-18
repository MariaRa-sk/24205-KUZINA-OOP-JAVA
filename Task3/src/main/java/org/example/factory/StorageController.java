package org.example.factory;

import org.example.factory.details.Accessory;
import org.example.factory.details.Body;
import org.example.factory.details.Car;
import org.example.factory.details.Motor;
import org.example.factory.people.Worker;
import java.util.concurrent.ThreadPoolExecutor;

public class StorageController implements Runnable {
    private final Storage<Car> carStorage;
    private final Storage<Body> bodyStorage;
    private final Storage<Motor> motorStorage;
    private final Storage<Accessory> accessoryStorage;
    private final Factory factory;
    private final ThreadPoolExecutor workersPool;
    private final Object monitor;

    public StorageController(Storage<Car> carStorage, Storage<Body> bodyStorage,
                             Storage<Motor> motorStorage, Storage<Accessory> accessoryStorage,
                             ThreadPoolExecutor workersPool, Object monitor,Factory factory) {
        this.carStorage = carStorage;
        this.bodyStorage = bodyStorage;
        this.motorStorage = motorStorage;
        this.accessoryStorage = accessoryStorage;
        this.workersPool = workersPool;
        this.monitor = monitor;
        this.factory = factory;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            int bodyAvailable = bodyStorage.getCurrentSize();
            int motorAvailable = motorStorage.getCurrentSize();
            int accAvailable = accessoryStorage.getCurrentSize();
            int emptySlots = carStorage.getCapacity() - carStorage.getCurrentSize();

            int canAssemble = Math.min(bodyAvailable, Math.min(motorAvailable, accAvailable));
            int tasksToCreate = Math.min(canAssemble, emptySlots);

            System.out.println("Контроллер: создаю " + tasksToCreate + " задач");
            for (int i = 0; i < tasksToCreate; i++) {
                workersPool.submit(new Worker(bodyStorage, motorStorage, accessoryStorage, carStorage, factory));
            }

            synchronized (monitor) {
                try {
                    monitor.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }
    }

    public void notifyController() {
        synchronized (monitor) {
            monitor.notify();
        }
    }
}
