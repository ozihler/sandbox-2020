package com.zihler.wiki.domain.values;

import com.zihler.wiki.domain.entity.WikiPage;

import java.util.Set;

import static java.util.stream.Collectors.toSet;

public class ReferencedWikiPagesDocument {
    private Set<WikiPageDocument> wikiPages;

    private ReferencedWikiPagesDocument(Set<WikiPageDocument> wikiPages) {
        this.wikiPages = wikiPages;
    }

    public static ReferencedWikiPagesDocument from(ReferencedWikiPages referencedWikiPages) {
        Set<WikiPageDocument> wikiPageDocuments = referencedWikiPages.getWikiPages().stream().map(WikiPage::asDocument).collect(toSet());
        return new ReferencedWikiPagesDocument(wikiPageDocuments);
    }

    public Set<WikiPageDocument> getWikiPages() {
        return wikiPages;
    }

    @Override
    public String toString() {
        return "";

    }
}