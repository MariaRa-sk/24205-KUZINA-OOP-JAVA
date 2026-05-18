package org.example.factory;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Config {
    private final int storageBodySize;
    private final int storageMotorSize;
    private final int storageAccessorySize;
    private final int storageAutoSize;
    private final int bodySuppliers;
    private final int motorSuppliers;
    private final int accessorySuppliers;
    private final int workers;
    private final int dealers;
    private final boolean logSale;

    public Config(String filePath){
        Properties props = new Properties();
        try(FileReader reader = new FileReader(filePath)){
            props.load(reader);
        }

        catch(IOException e){
            throw new RuntimeException("Не могу прочитать конфиг: " + filePath, e);
        }

        this.storageBodySize = Integer.parseInt(props.getProperty("StorageBodySize", "100"));
        this.storageMotorSize = Integer.parseInt(props.getProperty("StorageMotorSize", "100"));
        this.storageAccessorySize = Integer.parseInt(props.getProperty("StorageAccessorySize", "100"));
        this.storageAutoSize = Integer.parseInt(props.getProperty("StorageAutoSize", "100"));
        this.bodySuppliers =Integer.parseInt(props.getProperty("BodySuppliers", "5"));
        this.motorSuppliers =Integer.parseInt(props.getProperty("MotorSuppliers", "5"));
        this.accessorySuppliers =Integer.parseInt(props.getProperty("AccessorySuppliers", "5"));
        this.workers =Integer.parseInt(props.getProperty("Workers", "10"));
        this.dealers =Integer.parseInt(props.getProperty("Dealers", "20"));
        this.logSale = Boolean.parseBoolean(props.getProperty("LogSale", "true"));
    }

    public int getStorageBodySize(){
        return storageBodySize;
    }

    public int getStorageMotorSize(){
        return storageMotorSize;
    }
    public int getStorageAccessorySize(){
        return storageAccessorySize;
    }

    public int getStorageAutoSize(){
        return storageAutoSize;
    }

    public int getWorkers(){
        return workers;
    }

    public int getBodySuppliers(){
        return bodySuppliers;
    }

    public int getMotorSuppliers(){
        return motorSuppliers;
    }

    public int getAccessorySuppliers(){
        return accessorySuppliers;
    }

    public int getDealers(){
        return dealers;
    }

    public boolean isLogSale(){
        return logSale;
    }
}
