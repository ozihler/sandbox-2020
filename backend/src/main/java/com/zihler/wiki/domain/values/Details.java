package com.zihler.wiki.domain.values;

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
        return DetailsDocument.from(this);
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
