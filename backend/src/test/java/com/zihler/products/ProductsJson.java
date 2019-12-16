package com.zihler.products;

import java.util.List;

public class ProductsJson {
    private List<ProductJson> products;

    public ProductsJson(List<ProductJson> products) {
        this.products = products;
    }

    public List<ProductJson> getProducts() {
        return products;
    }
}
