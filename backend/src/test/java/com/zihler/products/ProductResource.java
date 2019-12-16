package com.zihler.products;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductResource {

    private final CreateProduct createProduct;
    private final CreateProducts createProducts;

    public ProductResource() {
        StoreProduct storeProduct = new InMemoryProductRepository();
        createProduct = new CreateProductUseCase(storeProduct);
        createProducts = new CreateProductsUseCase(storeProduct);
    }

    @PostMapping("/product")
    ResponseEntity<ProductJson> createProduct(@RequestBody ProductJson request) {
        CreateProductInput input = new CreateProductInput(request);

        RestProductPresenter output = new RestProductPresenter();

        createProduct.withInputs(input.productDocument(), output);

        return output.toResponseEntity();
    }

    @PostMapping("/products")
    ResponseEntity<ProductsJson> createProducts(@RequestBody ProductsJson request) {
        var input = new CreateProductsInput(request);
        var output = new RestProductsPresenter();
        createProducts.withInputs(input.productsDocument(), output);

        return output.toResponseEntity();
    }
}
