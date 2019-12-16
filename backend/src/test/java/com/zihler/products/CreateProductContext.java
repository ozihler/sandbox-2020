package com.zihler.products;

public class CreateProductContext implements Context {
    private CreatedProduct createdProduct;

    public CreateProductContext(CreatedProduct createdProduct) {
        this.createdProduct = createdProduct;
    }

    static CreateProductContext initializeWith(ProductDocument intendedProduct,
                                               StoreProduct storeProduct,
                                               ProductPresenter presenter) {
        var self = new Product(intendedProduct.getTitle());
        var createdProduct = new CreatedProduct(self, storeProduct, presenter);

        return new CreateProductContext(createdProduct);
    }

    @Override
    public void enactUseCase() {
        createdProduct.store().present();
    }
}
