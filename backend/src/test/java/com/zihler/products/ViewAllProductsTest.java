package com.zihler.products;


import com.zihler.products.pa.ObtainProducts;
import com.zihler.products.pa.ProductsRetriever;
import com.zihler.products.pa.RequestProducts;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ViewAllProductsTest {
    @Test
    void view_all_products_returns_all_products() {
        RetrieveAllProducts retrieveAllProducts = () -> List.of(
                new Product(1L, "p1"),
                new Product(2L, "p2")
        );

        ViewAllProducts useCase = new ViewAllProductsUseCase(retrieveAllProducts);

        ProductsPresenter presenter = products -> {
            assertEquals(1, products.get(0).getId().intValue());
            assertEquals(2, products.get(1).getId().intValue());
        };

        useCase.callWith(presenter);
    }

    @Test
    void view_all_products_returns_all_products_formatted_as_string_list() {
        RetrieveAllProducts retrieveAllProducts = () -> List.of(
                new Product(1L, "p1"),
                new Product(2L, "p2")
        );

        ViewAllProducts useCase = new ViewAllProductsUseCase(retrieveAllProducts);

        StringProductsPresenter presenter = new StringProductsPresenter();

        useCase.callWith(presenter);

        assertEquals("1. p1\n2. p2", presenter.getResponse());
    }

    @Test
    void viewAllProducts_PortsAdaptersStyle() {
        ObtainProducts obtainProducts = () -> List.of(
                new Product(1L, "p1"),
                new Product(2L, "p2")
        );

        RequestProducts productsRetriever = new ProductsRetriever(obtainProducts);

        List<Product> products = productsRetriever.fetchAllProducts();

        assertEquals("p1", products.get(0).getTitle());
        assertEquals("p2", products.get(1).getTitle());
    }

}
