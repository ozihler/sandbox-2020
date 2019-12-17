package com.zihler.wiki.domain.values;

public class Body implements Stringifiable {
    private String body;

    private Body(String body) {
        this.body = body;
    }

    public static Body from(String body) {
        return new Body(body);
    }

    public static Body empty() {
        return new Body("");
    }

    @Override
    public String asString() {
        return body;
    }

    @Override
    public String toString() {
        return body;
    }
}
