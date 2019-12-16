package com.zihler.products;

import static com.zihler.products.CreateProductContext.newContext;

public class CreateProductUseCase implements CreateProduct {
    private StoreProduct storeProduct;

    public CreateProductUseCase(StoreProduct storeProduct) {
        this.storeProduct = storeProduct;
    }

    @Override
    public void withInputs(ProductDocument intendedProduct, ProductPresenter productPresenter) {

        // this is a habit
        newContext(intendedProduct, storeProduct, productPresenter)
                .initialize()
                .enactUseCase();
    }

}
