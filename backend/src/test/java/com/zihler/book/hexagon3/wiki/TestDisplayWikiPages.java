package com.zihler.book.hexagon3.wiki;

import java.util.List;

public class TestDisplayWikiPages implements DisplayWikiPages {
    private List<WikiPage> wikiPages;

    @Override
    public void display(List<WikiPage> wikiPages) {
        this.wikiPages = wikiPages;
    }

    public List<WikiPage> getWikiPages() {
        return wikiPages;
    }
}
