package com.zihler.products;

public class Product {
    private Long id;
    private String title;

    public Product(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public Product(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }
}
