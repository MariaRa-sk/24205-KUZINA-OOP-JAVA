package org.example.factory.people;

import org.example.factory.Factory;
import org.example.factory.Storage;
import org.example.factory.details.Accessory;
import org.example.factory.details.Body;
import org.example.factory.details.Motor;
import org.example.factory.details.Car;

public class Worker implements Runnable {

    private final Storage<Body> bodyStorage;
    private final Storage<Motor> motorStorage;
    private final Storage<Accessory> accessoryStorage;
    private final Storage<Car> carStorage;
    private final Factory factory;

    public Worker(Storage<Body> b, Storage<Motor> m, Storage<Accessory> a, Storage<Car> c, Factory factory){
        this.bodyStorage = b;
        this.motorStorage = m;
        this.accessoryStorage = a;
        this.carStorage = c;
        this.factory = factory;
    }

    @Override
    public void run(){
        try{
            System.out.println(Thread.currentThread().getName() + ": жду кузов...");
            Body body = bodyStorage.get();
            System.out.println(Thread.currentThread().getName() + ": взял кузов");
            Motor motor = motorStorage.get();
            System.out.println(Thread.currentThread().getName() + ": взял мотор");
            Accessory accessory = accessoryStorage.get();
            System.out.println(Thread.currentThread().getName() + ": взял аксессуар");
            Car car = new Car(body, motor, accessory);
            carStorage.put(car);
            System.out.println(Thread.currentThread().getName() + ": собрал машину " + car.getId());
            factory.notifyListeners();
        }
        catch(InterruptedException e){
            Thread.currentThread().interrupt();
        }
    }
}
