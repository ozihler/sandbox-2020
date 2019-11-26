package com.zihler.wiki.domain.values;

public class Title {
    private String title;

    private Title(String title) {

        this.title = title;
    }

    public static Title from(String title) {
        return new Title(title);
    }

    public static Title empty() {
        return new Title("");
    }

    @Override
    public String toString() {
        return title;
    }
}
