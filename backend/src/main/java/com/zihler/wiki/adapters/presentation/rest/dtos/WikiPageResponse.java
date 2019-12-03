package com.zihler.wiki.adapters.presentation.rest.dtos;

import com.zihler.wiki.application.use_cases.outbound_ports.WikiPageDocument;

import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class WikiPageResponse implements Comparable<WikiPageResponse> {
    private final String referenceTag;
    private final String title;
    private final String body;

    private WikiPageResponse(String referenceTag, String title, String body) {
        this.referenceTag = referenceTag;
        this.title = title;
        this.body = body;
    }

    public static WikiPageResponse from(WikiPageDocument document) {
        return new WikiPageResponse(
                document.getReferenceTag().toString(),
                document.getTitle().toString(),
                document.getBody().toString()
        );
    }

    public static TreeSet<WikiPageResponse> from(SortedSet<WikiPageDocument> wikiPages) {
        return wikiPages.stream()
                .map(WikiPageResponse::from)
                .collect(Collectors.toCollection(TreeSet::new));
    }

    public String getReferenceTag() {
        return referenceTag;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    @Override
    public int compareTo(WikiPageResponse wikiPageResponse) {
        return this.referenceTag.compareTo(wikiPageResponse.referenceTag);
    }
}
