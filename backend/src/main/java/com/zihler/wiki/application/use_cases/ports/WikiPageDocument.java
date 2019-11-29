package com.zihler.wiki.application.use_cases.ports;

import com.zihler.wiki.domain.values.ReferenceTag;
import com.zihler.wiki.domain.values.Title;

public class WikiPageDocument {
    private ReferenceTag referenceTag;
    private Title title;
    private BodyDocument body;

    private WikiPageDocument(ReferenceTag referenceTag, Title title, BodyDocument body) {
        this.referenceTag = referenceTag;
        this.title = title;
        this.body = body;
    }

    public static WikiPageDocument of(ReferenceTag referenceTag, Title title, BodyDocument body) {
        return new WikiPageDocument(referenceTag, title, body);
    }

    public ReferenceTag getReferenceTag() {
        return referenceTag;
    }

    public Title getTitle() {
        return title;
    }

    public BodyDocument getBody() {
        return body;
    }


}
