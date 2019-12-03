package com.zihler.wiki.domain.values.search;

import com.zihler.wiki.domain.entity.WikiPage;

import java.util.TreeSet;

public class WikiPagesSearchResult {
    private TreeSet<WikiPage> wikiPages;

    private WikiPagesSearchResult(TreeSet<WikiPage> wikiPages) {
        this.wikiPages = wikiPages;
    }

    public static WikiPagesSearchResult from(TreeSet<WikiPage> wikiPages) {
        return new WikiPagesSearchResult(wikiPages);
    }

    public TreeSet<WikiPage> getWikiPages() {
        return wikiPages;
    }
}
