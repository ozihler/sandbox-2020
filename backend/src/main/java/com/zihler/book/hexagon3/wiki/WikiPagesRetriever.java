package com.zihler.book.hexagon3.wiki;

import java.util.List;

class WikiPagesRetriever implements RequestAllWikiPages {
    private ObtainWikiPages obtainWikiPages;

    WikiPagesRetriever(ObtainWikiPages obtainWikiPages) {
        this.obtainWikiPages = obtainWikiPages;
    }

    @Override
    public void fetchAllWikiPages(DisplayWikiPages displayWikiPages) {
        List<WikiPage> wikiPages = obtainWikiPages.obtainAll();
        displayWikiPages.display(wikiPages);
    }
}
