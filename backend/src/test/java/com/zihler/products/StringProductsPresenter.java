package com.zihler.products;

import java.util.List;

import static java.lang.String.format;
import static java.util.stream.Collectors.joining;

public class StringProductsPresenter implements ProductsPresenter {

    private String response;

    @Override
    public void present(List<Product> products) {
        this.response = products.stream()
                .map(product -> format("%d. %s", product.getId(), product.getTitle()))
                .collect(joining("\n"));
    }

    public String getResponse() {
        return response;
    }
}
