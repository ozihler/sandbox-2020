package com.zihler.wiki.domain.values;

import com.zihler.wiki.application.use_cases.outbound_ports.documents.WikiPageDocument;

import java.util.Set;

import static java.util.stream.Collectors.toSet;

public class WikiPagesDocument {
    private Set<WikiPageDocument> wikiPages;

    private WikiPagesDocument(Set<WikiPageDocument> wikiPages) {
        this.wikiPages = wikiPages;
    }

    public static WikiPagesDocument of(WikiPages wikiPages) {
        return new WikiPagesDocument(toDocuments(wikiPages));
    }

    private static Set<WikiPageDocument> toDocuments(WikiPages wikiPages) {
        return wikiPages.getWikiPages().stream().map(WikiPageDocument::of).collect(toSet());
    }

    public Set<WikiPageDocument> getWikiPages() {
        return wikiPages;
    }
}
