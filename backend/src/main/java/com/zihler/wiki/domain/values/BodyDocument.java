package com.zihler.wiki.domain.values;

public class BodyDocument {
    private String body;

    private BodyDocument(String body) {
        this.body = body;
    }

    public static BodyDocument from(Body body) {
        return new BodyDocument(body.get());
    }

    @Override
    public String toString() {
        return body;
    }

}
