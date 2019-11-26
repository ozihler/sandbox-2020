package com.zihler.wiki.domain.values;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import static java.lang.String.format;

public class Details {
    private final ReferenceTag referenceTag;
    private final Title title;
    private final Body body;

    public Details(ReferenceTag referenceTag, Title title, Body body) {
        this.referenceTag = referenceTag;
        this.title = title;
        this.body = body;
    }

    public DetailsDocument asDocument() {
        return DetailsDocument.of(this);
    }

    public ReferenceTag getReferenceTag() {
        return referenceTag;
    }

    public Title getTitle() {
        return title;
    }

    public Body getBody() {
        return body;
    }
}
