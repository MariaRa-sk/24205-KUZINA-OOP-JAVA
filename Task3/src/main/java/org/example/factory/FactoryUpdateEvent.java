package org.example.factory;


import org.example.factory.details.Product;
import org.example.factory.details.ProductType;

public class FactoryUpdateEvent {
    private final int bodyStorageSize;
    private final int bodyStorageCapacity;

    private final int motorStorageSize;
    private final int motorStorageCapacity;

    private final int accStorageSize;
    private final int accStorageCapacity;

    private final int carStorageSize;
    private final int carStorageCapacity;

    private final int bodyProduced;
    private final int motorProduced;
    private final int accProduced;
    private final int carProduced;

    private final int queueSize;

    public FactoryUpdateEvent(Factory factory) {
        this.bodyStorageSize = factory.getStorageBody().getCurrentSize();
        this.bodyStorageCapacity = factory.getStorageBody().getCapacity();

        this.motorStorageSize = factory.getStorageMotor().getCurrentSize();
        this.motorStorageCapacity = factory.getStorageMotor().getCapacity();

        this.accStorageSize = factory.getStorageAccessory().getCurrentSize();
        this.accStorageCapacity = factory.getStorageAccessory().getCapacity();

        this.carStorageSize = factory.getStorageCar().getCurrentSize();
        this.carStorageCapacity = factory.getStorageCar().getCapacity();

        this.bodyProduced = Product.getProducedCount(ProductType.BODY);
        this.motorProduced = Product.getProducedCount(ProductType.MOTOR);
        this.accProduced = Product.getProducedCount(ProductType.ACCESSORY);
        this.carProduced = Product.getProducedCount(ProductType.CAR);
        this.queueSize = factory.getWorkersPool().getQueue().size();
    }

    public int getBodyStorageSize() { return bodyStorageSize; }
    public int getBodyStorageCapacity() { return bodyStorageCapacity; }
    public int getMotorStorageSize() { return motorStorageSize; }
    public int getMotorStorageCapacity() { return motorStorageCapacity; }
    public int getAccStorageSize() { return accStorageSize; }
    public int getAccStorageCapacity() { return accStorageCapacity; }
    public int getCarStorageSize() { return carStorageSize; }
    public int getCarStorageCapacity() { return carStorageCapacity; }
    public int getBodyProduced() { return bodyProduced; }
    public int getMotorProduced() { return motorProduced; }
    public int getAccProduced() { return accProduced; }
    public int getCarProduced() { return carProduced; }
    public int getQueueSize() { return queueSize; }
}
