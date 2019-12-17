package com.zihler.wiki.adapters.presentation.rest.dtos;

import com.zihler.wiki.application.outbound_ports.documents.WikiPageDocument;

import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class WikiPageDto implements Comparable<WikiPageDto> {
    private String referenceTag;
    private String title;
    private String body;

    public WikiPageDto() {
    }

    private WikiPageDto(String referenceTag, String title, String body) {
        this.referenceTag = referenceTag;
        this.title = title;
        this.body = body;
    }

    public static WikiPageDto from(WikiPageDocument document) {
        return new WikiPageDto(
                document.referenceTag().toString(),
                document.title().toString(),
                document.body().toString()
        );
    }

    public static SortedSet<WikiPageDto> from(SortedSet<WikiPageDocument> wikiPages) {
        return wikiPages.stream()
                .map(WikiPageDto::from)
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
    public int compareTo(WikiPageDto wikiPageDto) {
        return referenceTag.compareTo(wikiPageDto.referenceTag);
    }
}
