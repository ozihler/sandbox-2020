package com.zihler.products;

public class CreatedProduct {
    private StoreProduct storeProduct;
    private Product self;

    public CreatedProduct(Product self, StoreProduct storeProduct) {
        this.self = self;
        this.storeProduct = storeProduct;
    }

    void store() {
        this.self = storeProduct.as(self);
    }

    public ProductDocument asDocument() {
        return ProductDocument.of(self);
    }
}
