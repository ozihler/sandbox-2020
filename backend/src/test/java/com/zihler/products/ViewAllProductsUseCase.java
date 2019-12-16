package com.zihler.products;

import java.util.List;

public class ViewAllProductsUseCase implements ViewAllProducts {

    private RetrieveAllProducts retrieveAllProducts;

    public ViewAllProductsUseCase(RetrieveAllProducts retrieveAllProducts) {
        this.retrieveAllProducts = retrieveAllProducts;
    }

    @Override
    public void callWith(ProductsPresenter presenter) {
        List<Product> allProducts = retrieveAllProducts.all();
        presenter.present(allProducts);
    }
}
