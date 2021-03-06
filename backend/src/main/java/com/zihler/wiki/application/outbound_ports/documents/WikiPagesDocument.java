package com.zihler.wiki.application.outbound_ports.documents;

import com.zihler.wiki.domain.values.WikiPages;

import java.util.LinkedHashSet;
import java.util.Set;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toSet;

public class WikiPagesDocument {
    private Set<WikiPageDocument> wikiPages;

    public WikiPagesDocument() {
        wikiPages = new LinkedHashSet<>();
    }

    public WikiPagesDocument(Set<WikiPageDocument> wikiPages) {
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

    @Override
    public String toString() {
        return "[\n" + wikiPages.stream().map(Object::toString).collect(joining(",\n")) + "\n]";
    }
}
