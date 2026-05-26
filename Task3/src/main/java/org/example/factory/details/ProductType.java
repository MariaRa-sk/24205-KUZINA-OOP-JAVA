package org.example.factory.details;

public enum ProductType {
    BODY(Body.class, "body-"),
    MOTOR(Motor.class, "motor-"),
    ACCESSORY(Accessory.class, "acs-"),
    CAR(Car.class, "car-");

    private final Class<? extends Product> clazz;
    private final String prefix;

    ProductType(Class<? extends Product> clazz, String prefix) {
        this.clazz = clazz;
        this.prefix = prefix;
    }

    public String getPrefix() {
        return prefix;
    }

    public static ProductType fromClass(Class<? extends Product> clazz) {
        for (ProductType type : values()) {
            if (type.clazz.equals(clazz)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown detail class: " + clazz.getSimpleName());
    }
}
