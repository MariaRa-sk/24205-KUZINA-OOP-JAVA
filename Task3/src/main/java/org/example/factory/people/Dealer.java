package org.example.factory.people;

import org.example.factory.Factory;
import org.example.factory.Storage;
import org.example.factory.StorageController;
import org.example.factory.details.Car;

public class Dealer implements Runnable{
    private final Factory factory;
    private volatile int delay;
    private final Storage<Car> storage;
    private final StorageController controller;


    public void setDelay(int delay){
        this.delay = delay;
    }

    public Dealer(Storage<Car> storage, StorageController controller, Factory factory){
        this.storage = storage;
        this.controller = controller;
        this.factory = factory;
    }


    @Override
    public void run(){

        while(!Thread.currentThread().isInterrupted()){
            try {
                Car car = storage.get();
                controller.notifyController();
                factory.notifyListeners();
                Thread.sleep(delay);
            }
            catch(InterruptedException e){
                Thread.currentThread().interrupt();
                break;
            }
        }

    }
}
