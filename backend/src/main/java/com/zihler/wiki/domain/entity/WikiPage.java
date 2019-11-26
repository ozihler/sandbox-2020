package com.zihler.wiki.domain.entity;

import com.zihler.wiki.domain.values.Details;
import com.zihler.wiki.domain.values.Id;
import com.zihler.wiki.domain.values.WikiPageDocument;

public class WikiPage {
    private Details details;
    private Id id;

    public WikiPage(Id id, Details details) {
        this.id = id;
        this.details = details;
    }

    public WikiPageDocument asDocument() {
        return WikiPageDocument.of(this);
    }

    public Details getDetails() {
        return details;
    }

    public Id getId() {
        return this.id;
    }
}
