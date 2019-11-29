package com.zihler.wiki.domain.values;

import com.zihler.wiki.application.use_cases.ports.BodyDocument;

import static java.lang.String.format;

public class DetailsDocument {
    private ReferenceTag referenceTag;
    private Title title;
    private BodyDocument body;

    private DetailsDocument(ReferenceTag referenceTag, Title title, BodyDocument body) {
        this.referenceTag = referenceTag;
        this.title = title;
        this.body = body;
    }

    public static DetailsDocument from(Details details) {
        return new DetailsDocument(
                details.getReferenceTag(),
                details.getTitle(),
                BodyDocument.from(""));
    }

    private DetailsDocument() {

    }

    @Override
    public String toString() {
        return format("{ 'referenceTag': '%s', 'title': '%s', 'body': '%s' }", referenceTag, title, body);
    }
}
