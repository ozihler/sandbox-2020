package com.zihler.products;

public class CreateProductsUseCase implements CreateProducts {
    private StoreProduct storeProduct;

    public CreateProductsUseCase(StoreProduct storeProduct) {
        this.storeProduct = storeProduct;
    }

    @Override
    public void withInputs(ProductsDocument intendedProducts, ProductPresenter productPresenter) {

        for (ProductDocument intendedProduct : intendedProducts.getProducts()) {
            // this is a habit
            CreateProductContext
                    .initialize(intendedProduct, storeProduct, productPresenter)
                    .enactUseCase();
        }
    }

}
