package com.zihler.wiki.domain.values;

import com.zihler.wiki.domain.entity.WikiPage;

public class _WikiPageDocument {
    private Id id;
    private DetailsDocument details;
    private ReferencedWikiPagesDocument referencedWikiPages;

    private _WikiPageDocument(Id id, DetailsDocument details, ReferencedWikiPagesDocument referencedWikiPages) {
        this.id = id;
        this.details = details;
        this.referencedWikiPages = referencedWikiPages;
    }

    public static _WikiPageDocument of(WikiPage wikiPage) {
        return new _WikiPageDocument(wikiPage.getId(), wikiPage.getDetails().asDocument(), wikiPage.getReferencedWikiPages().asDocument());
    }

    @Override
    public String toString() {
        return String.format("{'id': '%d', 'details': '%s', referencedWikiPages: '%s'}", id.longValue(), details.toString(), referencedWikiPages.toString());
    }

    public Id id() {
        return this.id;
    }

    public Id getId() {
        return id;
    }

    public DetailsDocument getDetails() {
        return details;
    }

    public ReferencedWikiPagesDocument getReferencedWikiPages() {
        return referencedWikiPages;
    }
}
