package com.zihler.wiki.adapters.presentation.rest.dto;

import com.zihler.wiki.application.outbound_ports.documents.WikiPageDocument;
import com.zihler.wiki.application.outbound_ports.documents.WikiPagesDocument;

import java.util.Set;

import static java.util.stream.Collectors.toSet;

public class WikiPagesDto {
    private Set<WikiPageDto> wikiPages;

    public WikiPagesDto() {
    }

    private WikiPagesDto(Set<WikiPageDto> wikiPages) {
        this.wikiPages = wikiPages;
    }

    public static WikiPagesDto from(WikiPagesDocument wikiPagesDocument) {
        return new WikiPagesDto(toResponses(wikiPagesDocument.getWikiPages()));
    }

    private static Set<WikiPageDto> toResponses(Set<WikiPageDocument> wikiPages) {
        return wikiPages.stream().map(WikiPageDto::from).collect(toSet());
    }

    public Set<WikiPageDto> getWikiPages() {
        return wikiPages;
    }
}
