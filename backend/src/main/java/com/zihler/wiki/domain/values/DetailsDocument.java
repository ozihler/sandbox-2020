package com.zihler.wiki.domain.values;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

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
                details.getBody().asDocument());
    }

    private DetailsDocument() {

    }

    @Override
    public String toString() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (IOException e) {
            return format("{ 'referenceTag': '%s', 'title': '%s', 'body': '%s' }", referenceTag, title, body);
        }
    }
}
