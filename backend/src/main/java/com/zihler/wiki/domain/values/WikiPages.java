package com.zihler.wiki.domain.values;

import com.zihler.wiki.domain.entity.WikiPage;

import java.util.LinkedHashSet;

public class WikiPages {
    private LinkedHashSet<WikiPage> wikiPages;

    public WikiPages(LinkedHashSet<WikiPage> wikiPages) {
        this.wikiPages = wikiPages;
    }

    public LinkedHashSet<WikiPage> getWikiPages() {
        return wikiPages;
    }
}
