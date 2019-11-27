package com.zihler.wiki.domain.values;

import com.zihler.wiki.domain.entity.WikiPage;

import java.util.Set;

public class ReferencedWikiPages {

    private Set<WikiPage> wikiPages;

    private ReferencedWikiPages(Set<WikiPage> wikiPages) {
        this.wikiPages = wikiPages;
    }

    public static ReferencedWikiPages from(Set<WikiPage> wikiPages) {
        return new ReferencedWikiPages(wikiPages);
    }

    ReferencedWikiPagesDocument asDocument() {
        return ReferencedWikiPagesDocument.from(this);
    }

    Set<WikiPage> getWikiPages() {
        return wikiPages;
    }
}
