package com.zihler.wiki.application.use_cases.outbound_ports;

import com.zihler.wiki.domain.values.search.WikiPagesSearchResult;

import java.util.SortedSet;

public class WikiPagesSearchResultDocument {
    private SortedSet<WikiPageDocument> wikiPages;

    private WikiPagesSearchResultDocument(SortedSet<WikiPageDocument> wikiPages) {
        this.wikiPages = wikiPages;
    }

    public static WikiPagesSearchResultDocument from(WikiPagesSearchResult searchResult) {
        return new WikiPagesSearchResultDocument(WikiPageDocument.toOrderedDocuments(searchResult));
    }

    public SortedSet<WikiPageDocument> getWikiPages() {
        return wikiPages;
    }
}
