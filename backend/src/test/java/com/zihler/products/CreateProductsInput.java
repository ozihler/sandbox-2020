package com.zihler.products;

public class CreateProductsInput {
    private ProductsJson request;

    public CreateProductsInput(ProductsJson request) {
        this.request = request;
    }

    public ProductsDocument productsDocument() {
        ProductsDocument productsDocument = new ProductsDocument();
        request.getProducts().stream().map(productJson -> new ProductDocument(productJson.getTitle())).forEach(productsDocument::add);
        return productsDocument;
    }
}
