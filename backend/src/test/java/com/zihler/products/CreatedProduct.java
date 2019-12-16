package com.zihler.products;

public class CreatedProduct {
    private Product self;
    private StoreProduct storeProduct;
    private ProductPresenter presenter;

    public CreatedProduct(Product self, StoreProduct storeProduct, ProductPresenter presenter) {
        this.self = self;
        this.storeProduct = storeProduct;
        this.presenter = presenter;
    }

    CreatedProduct store() {
        this.self = storeProduct.as(self);
        return this;
    }

    private ProductDocument asDocument() {
        return ProductDocument.of(self);
    }

    public void present() {
        presenter.present(asDocument());
    }
}
