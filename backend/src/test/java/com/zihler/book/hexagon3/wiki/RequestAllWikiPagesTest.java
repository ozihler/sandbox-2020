package com.zihler.book.hexagon3.wiki;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RequestAllWikiPagesTest {
    @Test
    void test() {
        WikiPage w1 = new WikiPage("title1");
        WikiPage w2 = new WikiPage("title2");
        List<WikiPage> wikiPages = List.of(w1, w2);

        ObtainWikiPages o = () -> wikiPages;

        TestDisplayWikiPages d = new TestDisplayWikiPages();

        RequestAllWikiPages requester = new WikiPagesRetriever(o);

        requester.fetchAllWikiPages(d);

        List<WikiPage> actualWikiPages = d.getWikiPages();

        assertEquals(2, actualWikiPages.size());
        assertEquals(w1.getTitle(), actualWikiPages.get(0).getTitle());
        assertEquals(w2.getTitle(), actualWikiPages.get(1).getTitle());
    }
}
