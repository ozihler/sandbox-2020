package com.zihler.wiki.adapters.presentation.rest.dtos;

import com.zihler.wiki.application.use_cases.outbound_ports.WikiPageDocument;
import com.zihler.wiki.domain.values.WikiPagesDocument;

import java.util.Set;

import static java.util.stream.Collectors.toSet;

public class WikiPagesResponse {
    Set<WikiPageResponse> wikiPages;

    private WikiPagesResponse(Set<WikiPageResponse> wikiPages) {
        this.wikiPages = wikiPages;
    }

    public static WikiPagesResponse from(WikiPagesDocument wikiPagesDocument) {
        return new WikiPagesResponse(toResponses(wikiPagesDocument.getWikiPages()));
    }

    private static Set<WikiPageResponse> toResponses(Set<WikiPageDocument> wikiPages) {
        return wikiPages.stream().map(WikiPageResponse::from).collect(toSet());
    }

    public Set<WikiPageResponse> getWikiPages() {
        return wikiPages;
    }
}
