package com.zihler.products;

import org.springframework.http.ResponseEntity;

public class RestProductPresenter implements ProductPresenter {
    private ResponseEntity<ProductJson> response;

    @Override
    public void present(ProductDocument productDocument) {
        ProductJson productJson = new ProductJson();
        productJson.setTitle(productDocument.getTitle());
        this.response = ResponseEntity.ok(productJson);
    }

    public ResponseEntity<ProductJson> toResponseEntity() {
        return this.response;
    }
}
