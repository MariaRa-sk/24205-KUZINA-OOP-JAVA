package org.example.factory;


import org.example.factory.details.Product;
import java.util.LinkedList;
import java.util.Queue;


public class Storage<T extends Product> {
    private final Queue<T> details = new LinkedList<>();
    int capacity;

    public Storage(int capacity) {
        this.capacity = capacity;
    }

    public synchronized void put(T detail) throws InterruptedException{
        while (details.size() == capacity){
            wait();
        }
        details.add(detail);
        notifyAll();
    }

    public synchronized T get() throws InterruptedException{
        while(details.isEmpty()){
            wait();
        }
        T detail = details.poll();
        notifyAll();
        return detail;
    }

    public int getCapacity(){
        return capacity;
    }

    public int getCurrentSize(){
        return details.size();
    }
}
