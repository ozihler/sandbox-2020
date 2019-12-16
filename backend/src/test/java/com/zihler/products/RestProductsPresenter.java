package com.zihler.products;

import org.springframework.http.ResponseEntity;

import static java.util.stream.Collectors.toList;

public class RestProductsPresenter implements ProductPresenter {
    private ProductsDocument productsDocument = new ProductsDocument();

    @Override
    public void present(ProductDocument productDocument) {
        productsDocument.add(productDocument);
    }

    public ResponseEntity<ProductsJson> toResponseEntity() {
        return ResponseEntity.ok(new ProductsJson(productsDocument.getProducts().stream().map(this::toJson).collect(toList())));
    }

    private ProductJson toJson(ProductDocument productDocument) {
        ProductJson productJson = new ProductJson();
        productJson.setTitle(productDocument.getTitle());
        return productJson;
    }

}
