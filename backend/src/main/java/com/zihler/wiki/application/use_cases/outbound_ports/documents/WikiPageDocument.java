package com.zihler.wiki.application.use_cases.outbound_ports.documents;

import com.zihler.wiki.domain.entity.WikiPage;
import com.zihler.wiki.domain.values.ReferenceTag;
import com.zihler.wiki.domain.values.Title;
import com.zihler.wiki.domain.values.search.WikiPagesSearchResult;

import java.util.SortedSet;
import java.util.TreeSet;

import static java.util.stream.Collectors.toCollection;

public class WikiPageDocument implements Comparable<WikiPageDocument> {
    private ReferenceTag referenceTag;
    private Title title;
    private BodyDocument body;

    private WikiPageDocument(ReferenceTag referenceTag, Title title, BodyDocument body) {
        this.referenceTag = referenceTag;
        this.title = title;
        this.body = body;
    }

    public static WikiPageDocument of(WikiPage wikiPage) {
        BodyDocument bodyDocument = BodyDocument.from(wikiPage.getBody());
        return new WikiPageDocument(wikiPage.getReferenceTag(), wikiPage.getTitle(), bodyDocument);
    }

    static SortedSet<WikiPageDocument> toOrderedDocuments(WikiPagesSearchResult searchResult) {
        return searchResult.getWikiPages()
                .stream()
                .map(WikiPageDocument::of)
                .collect(toCollection(TreeSet::new));
    }

    public ReferenceTag getReferenceTag() {
        return referenceTag;
    }

    public Title getTitle() {
        return title;
    }

    public BodyDocument getBody() {
        return body;
    }

    @Override
    public int compareTo(WikiPageDocument wikiPageDocument) {
        return this.referenceTag.compareTo(wikiPageDocument.referenceTag);
    }
}
