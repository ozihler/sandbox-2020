package com.zihler.products;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductResource {

    private final CreateProductUseCase createProduct;

    public ProductResource() {
        StoreProduct storeProduct = new InMemoryProductRepository();
        createProduct = new CreateProductUseCase(storeProduct);
    }

    @PostMapping("/products")
    ResponseEntity<ProductJson> createProduct(@RequestBody ProductJson request) {
        CreateProductInput input = new CreateProductInput(request);

        RestProductPresenter output = new RestProductPresenter();

        ProductDocument productToCreate = input.productDocument();

        createProduct.withInputs(productToCreate, output);

        return output.toResponseEntity();
    }
}
