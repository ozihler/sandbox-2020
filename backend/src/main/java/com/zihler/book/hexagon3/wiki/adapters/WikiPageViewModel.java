package com.zihler.book.hexagon3.wiki.adapters;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class WikiPageViewModel {
    private String title;

    @JsonCreator
    public WikiPageViewModel(@JsonProperty("title") String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
