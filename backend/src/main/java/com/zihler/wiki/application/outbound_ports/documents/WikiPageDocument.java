package com.zihler.wiki.application.outbound_ports.documents;

import com.zihler.wiki.domain.entity.WikiPage;
import com.zihler.wiki.domain.values.ReferenceTag;
import com.zihler.wiki.domain.values.Title;

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

    public static WikiPageDocument from(Title title, ReferenceTag referenceTag) {
        return new WikiPageDocument(referenceTag, title, null);
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
        return referenceTag.compareTo(wikiPageDocument.referenceTag);
    }
}
