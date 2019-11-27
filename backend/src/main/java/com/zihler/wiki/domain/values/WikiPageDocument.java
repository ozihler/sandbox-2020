package com.zihler.wiki.domain.values;

import com.zihler.wiki.domain.entity.WikiPage;

public class WikiPageDocument {
    private Id id;
    private DetailsDocument details;
    private ReferencedWikiPagesDocument referencedWikiPages;

    private WikiPageDocument(Id id, DetailsDocument details, ReferencedWikiPagesDocument referencedWikiPages) {
        this.id = id;
        this.details = details;
        this.referencedWikiPages = referencedWikiPages;
    }

    public static WikiPageDocument of(WikiPage wikiPage) {
        return new WikiPageDocument(wikiPage.getId(), wikiPage.getDetails().asDocument(), wikiPage.getReferencedWikiPages().toDocument());
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
