package org.example.factory.people;
import org.example.factory.Factory;
import org.example.factory.Storage;
import org.example.factory.StorageController;
import org.example.factory.details.Product;

import java.util.concurrent.atomic.AtomicInteger;

public class Supplier<T extends Product> implements Runnable{
    private final Storage<T> storage;
    private final Class<T> detailClass;
    private volatile int delay;
    private final AtomicInteger producedDetailCount;
    private final Factory factory;
    private final StorageController controller;

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public Supplier(Storage<T> storage, Class<T> detailClass, Factory factory, StorageController controller){
        this.delay = 1000;
        this.storage = storage;
        this.detailClass = detailClass;
        this.producedDetailCount = new AtomicInteger(0);
        this.factory = factory;
        this.controller = controller;
    }

    private T makeDetail() {
        try {
            return detailClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }


    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                T detail = makeDetail();
                int sizeBefore = storage.getCurrentSize();
                storage.put(detail);
                producedDetailCount.incrementAndGet();
                controller.notifyController();

                factory.notifyListeners();
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

}
