package com.zihler.wiki.domain.values;

import com.zihler.wiki.application.use_cases.ports.BodyDocument;

public class Body {
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

    public ReferenceTags foundReferenceTags() {
        return ReferenceTags.from(this);
    }

    public BodyDocument asDocument() {
        return BodyDocument.from(this);
    }

    public String get() {
        return body;
    }
}
