package com.zihler.wiki.application.outbound_ports.documents;

import com.zihler.wiki.domain.entity.WikiPage;
import com.zihler.wiki.domain.values.Body;
import com.zihler.wiki.domain.values.ReferenceTag;
import com.zihler.wiki.domain.values.ReferencedWikiPages;
import com.zihler.wiki.domain.values.Title;

import java.util.Objects;

public class WikiPageDocument implements Comparable<WikiPageDocument> {
    private ReferenceTag referenceTag;
    private Title title;
    private Body body;
    private ReferencedWikiPages referencedWikiPages;

    private WikiPageDocument(ReferenceTag referenceTag, Title title, Body body, ReferencedWikiPages referencedWikiPages) {
        this.referenceTag = referenceTag;
        this.title = title;
        this.body = body;
        this.referencedWikiPages = referencedWikiPages;
    }

    public static WikiPageDocument of(WikiPage wikiPage) {
        return from(wikiPage.getReferenceTag(), wikiPage.getTitle(), wikiPage.getBody(), wikiPage.getReferencedWikiPages());
    }

    public static WikiPageDocument from(ReferenceTag referenceTag, Title title, Body body, ReferencedWikiPages referencedWikiPages) {
        return new WikiPageDocument(referenceTag, title, body, referencedWikiPages);
    }

    public static WikiPageDocument from(ReferenceTag referenceTag, Title title, Body body) {
        return from(referenceTag, title, body, ReferencedWikiPages.empty());
    }

    public static WikiPageDocument from(ReferenceTag referenceTag, Title title) {
        return from(referenceTag, title, Body.empty(), ReferencedWikiPages.empty());
    }


    public ReferenceTag referenceTag() {
        return referenceTag;
    }

    public Title title() {
        return title;
    }

    public Body body() {
        return body;
    }

    public ReferencedWikiPages referencedWikiPages() {
        return referencedWikiPages;
    }

    @Override
    public int compareTo(WikiPageDocument wikiPageDocument) {
        return referenceTag.compareTo(wikiPageDocument.referenceTag);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WikiPageDocument document = (WikiPageDocument) o;
        return Objects.equals(referenceTag, document.referenceTag) &&
                Objects.equals(title, document.title) &&
                Objects.equals(body, document.body) &&
                Objects.equals(referencedWikiPages, document.referencedWikiPages);
    }

    @Override
    public int hashCode() {
        return Objects.hash(referenceTag, title, body, referencedWikiPages);
    }

    @Override
    public String toString() {
        return "{" +
                "\n\t\"referenceTag\": \"" + referenceTag + "\"," +
                "\n\t\"title\" \":" + title + "\"," +
                "\n\t\"body\": \"" + body + "\"" +
                "\n\t\"referencedWikiPages\": \"" + referencedWikiPages + "\"" +
                "\n}";
    }

}
