package com.zihler.products;

import java.util.HashMap;
import java.util.Map;

public class InMemoryProductRepository implements StoreProduct {
    private static long productIdCounter = 1;

    private Map<Long, Product> products = new HashMap<>();

    @Override
    public Product as(Product product) {
        long nextId = productIdCounter++;
        product.setId(nextId);
        products.put(nextId, product);
        return product;
    }
}
