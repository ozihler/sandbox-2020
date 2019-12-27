package com.zihler.book.hexagon3.wiki;

import com.zihler.book.hexagon3.wiki.adapters.RestDisplayWikiPages;
import com.zihler.book.hexagon3.wiki.adapters.WikiPageViewModel;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestRestDisplayWikiPages {
    @Test
    void test() {
        // given
        WikiPage w1 = new WikiPage("title1");
        WikiPage w2 = new WikiPage("title2");
        List<WikiPage> wikiPages = List.of(w1, w2);

        // when
        RestDisplayWikiPages displayWikiPages = new RestDisplayWikiPages();
        displayWikiPages.display(wikiPages);

        // then
        var result = displayWikiPages.getWikiPages();
        assertEquals(HttpStatus.OK, result.getStatusCode());

        var wikiPageViewModels = result.getBody();
        assertEquals(2, wikiPageViewModels.size());

        for (int i = 0; i < wikiPages.size(); ++i) {
            WikiPage expectedWikiPage = wikiPages.get(i);
            WikiPageViewModel actualWikiPage = wikiPageViewModels.get(i);

            String expectedTitle = "<h1>" + expectedWikiPage.getTitle() + "</h1>";
            String actualTitle = actualWikiPage.getTitle();

            assertEquals(expectedTitle, actualTitle);
        }
    }
}
