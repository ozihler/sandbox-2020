package com.zihler.products;

import java.util.ArrayList;
import java.util.List;

public class CreateProductUseCase implements CreateProduct {
    private StoreProduct storeProduct;

    public CreateProductUseCase(StoreProduct storeProduct) {
        this.storeProduct = storeProduct;
    }

    @Override
    public void withInputs(ProductDocument intendedProduct, ProductPresenter productPresenter) {

        // this is a habit
        CreateProductContext
                .initialize(intendedProduct, storeProduct, productPresenter)
                .enactUseCase();
    }

    public void createPrducts(List<ProductDocument> intendedProducts) {

        List<ProductDocument> createdProducts = new ArrayList<>();
        ProductPresenter productPresenter = new ProductPresenter() {
            @Override
            public void present(ProductDocument productDocument) {
                createdProducts.add(productDocument);
            }
        };

        for (ProductDocument intendedProduct : intendedProducts) {
            // this is a habit
            CreateProductContext
                    .initialize(intendedProduct, storeProduct, productPresenter)
                    .enactUseCase();
        }

        for (ProductDocument createdProduct : createdProducts) {
            System.out.println(createdProduct.getTitle());
        }
    }

}
