package com.zihler.wiki.application.use_cases.ports;

import com.zihler.wiki.domain.values.ReferenceTag;

public class WikiPageDocument {
    private final ReferenceTag referenceTag;
    private final BodyDocument body;

    private WikiPageDocument(ReferenceTag referenceTag, BodyDocument body) {
        this.referenceTag = referenceTag;
        this.body = body;
    }

    public static WikiPageDocument of(ReferenceTag referenceTag, BodyDocument body) {
        return new WikiPageDocument(referenceTag, body);
    }

    public ReferenceTag getReferenceTag() {
        return referenceTag;
    }

    public BodyDocument getBody() {
        return body;
    }
}
