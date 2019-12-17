package com.zihler.wiki.domain.entity;

import com.zihler.wiki.domain.values.Body;
import com.zihler.wiki.domain.values.ReferenceTag;
import com.zihler.wiki.domain.values.Title;

public class WikiPage implements Comparable<WikiPage> {
    private ReferenceTag referenceTag;
    private Title title;
    private Body body;

    private WikiPage(ReferenceTag referenceTag, Title title, Body body) {
        this.referenceTag = referenceTag;
        this.title = title;
        this.body = body;
    }

    private WikiPage() {

    }

    public static WikiPage from(ReferenceTag referenceTag, Title title, Body body) {
        return new WikiPage(referenceTag, title, body);
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
}
