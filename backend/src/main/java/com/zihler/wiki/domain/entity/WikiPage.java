package com.zihler.wiki.domain.entity;

import com.zihler.wiki.domain.values.Body;
import com.zihler.wiki.domain.values.ReferenceTag;
import com.zihler.wiki.domain.values.Title;

public class WikiPage implements Comparable<WikiPage> {
    private final ReferenceTag referenceTag;
    private final Title title;
    private final Body body;

    private WikiPage(ReferenceTag referenceTag, Title title, Body body) {
        this.referenceTag = referenceTag;
        this.title = title;
        this.body = body;
    }

    public static WikiPage from(ReferenceTag referenceTag, Title title) {
        return new WikiPage(referenceTag, title, Body.empty());
    }

    public static WikiPage from(ReferenceTag referenceTag) {
        return from(referenceTag, Title.from(referenceTag));
    }

    public ReferenceTag getReferenceTag() {
        return referenceTag;
    }

    public Title getTitle() {
        return title;
    }

    public Body getBody() {
        return body;
    }

    @Override
    public int compareTo(WikiPage wikiPage) {
        return this.referenceTag.compareTo(wikiPage.referenceTag);
    }
}
