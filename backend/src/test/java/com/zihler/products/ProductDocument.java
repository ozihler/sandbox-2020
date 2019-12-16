package com.zihler.products;

public class ProductDocument {
    private String title;

    public ProductDocument(String title) {
        this.title = title;
    }

    static ProductDocument of(Product product) {
        return new ProductDocument(product.getTitle());
    }

    public String getTitle() {
        return title;
    }
}
