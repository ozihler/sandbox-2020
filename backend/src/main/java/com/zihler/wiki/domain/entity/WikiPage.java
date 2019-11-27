package com.zihler.wiki.domain.entity;

import com.zihler.wiki.domain.values.Details;
import com.zihler.wiki.domain.values.Id;
import com.zihler.wiki.domain.values.ReferencedWikiPages;
import com.zihler.wiki.domain.values.WikiPageDocument;

public class WikiPage {
    private Id id;
    private Details details;
    private ReferencedWikiPages referencedWikiPages;

    public WikiPage(Id id, Details details, ReferencedWikiPages referencedWikiPages) {
        this.id = id;
        this.details = details;
        this.referencedWikiPages = referencedWikiPages;
    }


    private WikiPage(Details details, ReferencedWikiPages referencedWikiPages) {
        this(Id.empty(), details, referencedWikiPages);
    }

    public static WikiPage from(Details details) {
        return new WikiPage(details, referencedWikiPagesFrom(details));
    }

    private static ReferencedWikiPages referencedWikiPagesFrom(Details details) {
        return details.getBody()
                .foundReferenceTags()
                .toReferencedDetails()
                .toReferencedWikiPages();
    }

    public WikiPageDocument asDocument() {
        return WikiPageDocument.of(this);
    }

    public Details getDetails() {
        return details;
    }

    public Id getId() {
        return this.id;
    }

    public ReferencedWikiPages getReferencedWikiPages() {
        return referencedWikiPages;
    }
}
