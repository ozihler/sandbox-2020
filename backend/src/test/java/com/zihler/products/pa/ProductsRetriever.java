package com.zihler.products.pa;

import com.zihler.products.Product;

import java.util.List;

public class ProductsRetriever implements RequestProducts {
    private ObtainProducts obtainProducts;

    public ProductsRetriever(ObtainProducts obtainProducts) {
        this.obtainProducts = obtainProducts;
    }

    @Override
    public List<Product> fetchAllProducts() {
        return obtainProducts.all();
    }
}
