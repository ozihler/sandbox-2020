package com.zihler.wiki.domain.values;

import com.zihler.wiki.domain.entity.WikiPage;

import java.util.Set;

public class WikiPages {
    private Set<WikiPage> wikiPages;

    public WikiPages(Set<WikiPage> wikiPages) {
        this.wikiPages = wikiPages;
    }

    public Set<WikiPage> getWikiPages() {
        return wikiPages;
    }
}
