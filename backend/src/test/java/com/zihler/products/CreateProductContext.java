package com.zihler.products;

public class CreateProductContext implements Context {

    private final ProductDocument product;
    private final StoreProduct storeProduct;
    private final ProductPresenter presenter;
    private CreatedProduct createdProduct;

    private CreateProductContext(ProductDocument product,
                                 StoreProduct storeProduct,
                                 ProductPresenter presenter) {
        this.product = product;
        this.storeProduct = storeProduct;
        this.presenter = presenter;
    }

    static CreateProductContext newContext(ProductDocument product,
                                           StoreProduct storeProduct,
                                           ProductPresenter presenter) {

        return new CreateProductContext(product, storeProduct, presenter);
    }

    public CreateProductContext initialize() {
        var self = new Product(product.getTitle());
        this.createdProduct = new CreatedProduct(self, storeProduct, presenter);

        return this;
    }

    @Override
    public void enactUseCase() {
        createdProduct.store()
                .present();
    }
}
