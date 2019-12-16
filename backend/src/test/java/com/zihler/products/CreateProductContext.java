package com.zihler.products;

public class CreateProductContext implements Context {

    private CreatedProduct createdProduct;
    private ProductPresenter productPresenter;

    private CreateProductContext(ProductDocument intendedProduct, StoreProduct storeProduct, ProductPresenter productPresenter) {
        this.productPresenter = productPresenter;
        this.createdProduct = new CreatedProduct(new Product(intendedProduct.getTitle()), storeProduct);
    }

    public static CreateProductContext initialize(ProductDocument intendedProduct, StoreProduct storeProduct, ProductPresenter productPresenter) {
        return new CreateProductContext(intendedProduct, storeProduct, productPresenter);
    }

    @Override
    public void enactUseCase() {
        createdProduct.store();
        ProductDocument document = createdProduct.asDocument();
        productPresenter.present(document);
    }
}
