package com.zihler.products;

public class CreateProductInput {
    private ProductJson request;

    public CreateProductInput(ProductJson request) {
        this.request = request;
    }

    ProductDocument productDocument() {
        if (request.getTitle() == null) {
            throw new InvalidTitleException("Title cannot be null!");
        }
        return new ProductDocument(request.getTitle());
    }
}
