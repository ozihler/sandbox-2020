package com.zihler.wiki.application.use_cases.ports;

import com.zihler.wiki.domain.values.Body;

public class BodyDocument {
    private String body;

    private BodyDocument(String body) {
        this.body = body;
    }

    public static BodyDocument from(Body body) {
        return new BodyDocument(body.get());
    }

    public static BodyDocument from(String body) {
        return new BodyDocument(body);
    }

    @Override
    public String toString() {
        return body;
    }

}
