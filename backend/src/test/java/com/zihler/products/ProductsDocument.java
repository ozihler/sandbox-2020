package com.zihler.products;

import java.util.ArrayList;
import java.util.List;

public class ProductsDocument {
    private List<ProductDocument> products = new ArrayList<>();

    public List<ProductDocument> getProducts() {
        return this.products;
    }

    public void add(ProductDocument product) {
        products.add(product);
    }
}
