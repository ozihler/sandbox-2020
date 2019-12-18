package com.zihler.wiki.domain.entity;

import com.zihler.wiki.domain.values.Body;
import com.zihler.wiki.domain.values.ReferenceTag;
import com.zihler.wiki.domain.values.ReferencedWikiPages;
import com.zihler.wiki.domain.values.Title;

import java.util.Objects;

public class WikiPage implements Comparable<WikiPage> {
    private ReferenceTag referenceTag;
    private Title title;
    private Body body;
    private ReferencedWikiPages referencedWikiPages;

    private WikiPage(ReferenceTag referenceTag, Title title, Body body, ReferencedWikiPages referencedWikiPages) {
        this.referenceTag = referenceTag;
        this.title = title;
        this.body = body;
        this.referencedWikiPages = referencedWikiPages;
    }

    private WikiPage() {
    }

    public static WikiPage from(ReferenceTag referenceTag, Title title, Body body) {
        ReferencedWikiPages referencedWikiPages = ReferencedWikiPages.empty();
        return from(referenceTag, title, body, referencedWikiPages);
    }

    public static WikiPage from(ReferenceTag referenceTag, Title title, Body body, ReferencedWikiPages referencedWikiPages) {
        return new WikiPage(referenceTag, title, body, referencedWikiPages);
    }

    public static WikiPage empty() {
        return new WikiPage();
    }

    public ReferenceTag getReferenceTag() {
        return referenceTag;
    }

    public void setReferenceTag(ReferenceTag referenceTag) {
        this.referenceTag = referenceTag;
    }

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    @Override
    public int compareTo(WikiPage wikiPage) {
        return referenceTag.compareTo(wikiPage.referenceTag);
    }

    public void add(ReferenceTag referencedWikiPage) {
        referencedWikiPages.add(referencedWikiPage);
    }

    public ReferencedWikiPages getReferencedWikiPages() {
        return referencedWikiPages;
    }

    public void setReferencedWikiPages(ReferencedWikiPages referencedWikiPages) {
        this.referencedWikiPages = referencedWikiPages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WikiPage wikiPage = (WikiPage) o;
        return Objects.equals(referenceTag, wikiPage.referenceTag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(referenceTag);
    }
}
