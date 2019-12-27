package com.zihler.book.hexagon3.wiki.adapters;

import com.zihler.book.hexagon3.wiki.DisplayWikiPages;
import com.zihler.book.hexagon3.wiki.WikiPage;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class RestDisplayWikiPages implements DisplayWikiPages {
    private ResponseEntity<List<WikiPageViewModel>> wikiPages;

    public ResponseEntity<List<WikiPageViewModel>> getWikiPages() {
        return wikiPages;
    }

    @Override
    public void display(List<WikiPage> wikiPages) {
        var wikiPagesViewModels = format(wikiPages);
        this.wikiPages = ResponseEntity.ok(wikiPagesViewModels);
    }

    private List<WikiPageViewModel> format(List<WikiPage> wikiPages) {
        return wikiPages.stream()
                .map(this::toViewModel)
                .collect(toList());
    }

    private WikiPageViewModel toViewModel(WikiPage wikiPage) {
        return new WikiPageViewModel("<h1>" + wikiPage.getTitle() + "</h1>");
    }
}
