package com.zihler.wiki.application.outbound_ports.documents;

import com.zihler.wiki.domain.entity.WikiPage;
import com.zihler.wiki.domain.values.ReferenceTag;
import com.zihler.wiki.domain.values.Title;

import java.util.Objects;

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
        return from(wikiPage.getReferenceTag(), wikiPage.getTitle(), bodyDocument);
    }

    public static WikiPageDocument from(ReferenceTag referenceTag, Title title, BodyDocument body) {
        return new WikiPageDocument(referenceTag, title, body);
    }


    public ReferenceTag referenceTag() {
        return referenceTag;
    }

    public Title title() {
        return title;
    }

    public BodyDocument body() {
        return body;
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
                Objects.equals(body, document.body);
    }

    @Override
    public int hashCode() {
        return Objects.hash(referenceTag, title, body);
    }

    @Override
    public String toString() {
        return "{" +
                "\n\t\"referenceTag\": \"" + referenceTag + "\"," +
                "\n\t\"title\" \":" + title + "\"," +
                "\n\t\"body\": \"" + body + "\"" +
                "\n}";
    }

}
