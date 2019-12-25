package com.zihler.wiki.adapters.presentation.rest.dtos;

import com.zihler.wiki.application.outbound_ports.documents.WikiPageDocument;
import com.zihler.wiki.domain.values.ReferenceTag;
import com.zihler.wiki.domain.values.ReferencedWikiPages;

import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toSet;

public class WikiPageDto implements Comparable<WikiPageDto> {
    private String referenceTag;
    private String title;
    private String body;
    private Set<String> referencedWikiPages;

    public WikiPageDto() {
    }

    private WikiPageDto(String referenceTag, String title, String body, Set<String> referencedWikiPages) {
        this.referenceTag = referenceTag;
        this.title = title;
        this.body = body;
        this.referencedWikiPages = referencedWikiPages;
    }

    public static WikiPageDto from(WikiPageDocument document) {
        return new WikiPageDto(
                document.referenceTag().toString(),
                document.title().toString(),
                document.body().toString(),
                toReferenceTagStrings(document.referencedWikiPages())
        );
    }

    private static Set<String> toReferenceTagStrings(ReferencedWikiPages referencedWikiPages) {
        return referencedWikiPages.getReferencedWikiPages().stream().map(ReferenceTag::toString).collect(toSet());
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

    public Set<String> getReferencedWikiPages() {
        return referencedWikiPages;
    }

    @Override
    public int compareTo(WikiPageDto wikiPageDto) {
        return referenceTag.compareTo(wikiPageDto.referenceTag);
    }
}
