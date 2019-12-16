package com.zihler.products;

public class CreateProductUseCase implements CreateProduct {
    private StoreProduct storeProduct;

    public CreateProductUseCase(StoreProduct storeProduct) {
        this.storeProduct = storeProduct;
    }

    @Override
    public void withInputs(ProductDocument productToCreate, ProductPresenter productPresenter) {
        Product product = new Product(productToCreate.getTitle());
        Product storedProduct = storeProduct.as(product);
        ProductDocument productDocument = ProductDocument.of(storedProduct);
        productPresenter.present(productDocument);
    }

}
