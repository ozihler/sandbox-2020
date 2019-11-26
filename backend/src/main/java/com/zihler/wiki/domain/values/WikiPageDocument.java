package com.zihler.wiki.domain.values;

import com.zihler.wiki.domain.entity.WikiPage;

public class WikiPageDocument {
    private Id id;
    private DetailsDocument detailsDocument;

    private WikiPageDocument(Id id, DetailsDocument detailsDocument) {
        this.id = id;
        this.detailsDocument = detailsDocument;
    }

    public static WikiPageDocument of(WikiPage wikiPage) {
        return new WikiPageDocument(wikiPage.getId(), wikiPage.getDetails().asDocument());
    }

    @Override
    public String toString() {
        return detailsDocument.toString();
    }

    public Id id() {
        return this.id;
    }
}
