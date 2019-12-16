package com.zihler.products;

import com.fasterxml.jackson.annotation.JsonInclude;

public class ProductJson {
    @JsonInclude
    private String title;

    public ProductJson() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
