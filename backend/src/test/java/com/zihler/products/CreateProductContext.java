package com.zihler.products;

public class CreateProductContext implements Context {

    private CreatedProduct createdProduct;

    private CreateProductContext(CreatedProduct createdProduct) {
        this.createdProduct = createdProduct;
    }

    public static CreateProductContext initialize(
            ProductDocument product,
            StoreProduct storeProduct,
            ProductPresenter presenter) {

        var self = new Product(product.getTitle());
        var createdProduct = new CreatedProduct(self, storeProduct, presenter);

        return new CreateProductContext(createdProduct);
    }

    @Override
    public void enactUseCase() {
        createdProduct.store()
                .present();
    }
}
