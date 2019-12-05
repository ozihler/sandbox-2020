package com.zihler.wiki.application.use_cases.outbound_ports.documents;

import com.zihler.wiki.domain.values.Body;

import java.util.Objects;

public class BodyDocument {
    private String body;

    private BodyDocument(String body) {
        this.body = body;
    }

    public static BodyDocument from(Body body) {
        return new BodyDocument(body.asString());
    }

    public static BodyDocument from(String body) {
        return new BodyDocument(body);
    }

    @Override
    public String toString() {
        return body;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BodyDocument that = (BodyDocument) o;
        return Objects.equals(body, that.body);
    }

    @Override
    public int hashCode() {
        return Objects.hash(body);
    }
}
