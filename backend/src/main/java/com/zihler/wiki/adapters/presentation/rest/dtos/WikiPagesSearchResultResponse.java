package com.zihler.wiki.adapters.presentation.rest.dtos;

import com.zihler.wiki.application.use_cases.outbound_ports.WikiPageDocument;
import com.zihler.wiki.application.use_cases.outbound_ports.WikiPagesSearchResultDocument;

import java.util.SortedSet;

public class WikiPagesSearchResultResponse {
    SortedSet<WikiPageResponse> wikiPages;

    private WikiPagesSearchResultResponse(SortedSet<WikiPageDocument> wikiPages) {
        this.wikiPages = WikiPageResponse.from(wikiPages);
    }

    public static WikiPagesSearchResultResponse from(WikiPagesSearchResultDocument wikiPagesSearchResultDocument) {
        return new WikiPagesSearchResultResponse(wikiPagesSearchResultDocument.getWikiPages());
    }

    public SortedSet<WikiPageResponse> getWikiPages() {
        return wikiPages;
    }
}
