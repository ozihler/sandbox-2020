package com.zihler.products;

public class CreateProductUseCase implements CreateProduct {
    private StoreProduct storeProduct;

    public CreateProductUseCase(StoreProduct storeProduct) {
        this.storeProduct = storeProduct;
    }

    @Override
    public void withInputs(ProductDocument intendedProduct, ProductPresenter productPresenter) {

        // this is a habit
        CreateProductContext
                .initialize(intendedProduct, storeProduct, productPresenter)
                .enactUseCase();
    }

}
