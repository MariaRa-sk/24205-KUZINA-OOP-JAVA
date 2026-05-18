package org.example.factory.details;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class Product {
    private static final ConcurrentHashMap<ProductType, AtomicInteger> counters = new ConcurrentHashMap<>();
    private final String id;

    public Product() {
        ProductType type = ProductType.fromClass(this.getClass());
        counters.putIfAbsent(type, new AtomicInteger(0));
        int num = counters.get(type).getAndIncrement();
        this.id = type.getPrefix() + num;
    }

    public String getId() {
        return id;
    }

    public static int getProducedCount(ProductType type) {
        AtomicInteger count = counters.get(type);
        return count != null ? count.get() : 0;
    }
}
