package com.zihler.wiki.adapters.presentation.rest;

import com.zihler.wiki.adapters.facades.SpringWikiPagesFacade;
import com.zihler.wiki.adapters.presentation.rest.dto.WikiPageDto;
import com.zihler.wiki.adapters.presentation.rest.dto.WikiPagesDto;
import com.zihler.wiki.adapters.presentation.rest.input.CreateWikiPageFromTitleInput;
import com.zihler.wiki.adapters.presentation.rest.input.CreateWikiPagesFromBodyInput;
import com.zihler.wiki.adapters.presentation.rest.presenter.RestWikiPagePresenter;
import com.zihler.wiki.adapters.presentation.rest.presenter.RestWikiPagesPresenter;
import com.zihler.wiki.adapters.presentation.rest.presenter.RestWikiPagesSearchResultPresenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "wiki/pages", produces = "application/json")
public class WikiPageResource {
    private SpringWikiPagesFacade wikiPagesFacade;

    @Autowired
    public WikiPageResource(SpringWikiPagesFacade wikiPagesFacade) {
        this.wikiPagesFacade = wikiPagesFacade;
    }

    @PostMapping(path = "/title")
    public ResponseEntity<WikiPageDto> createWikiPageFromTitle(@RequestBody WikiPageDto request) {
        var input = new CreateWikiPageFromTitleInput(request);

        var output = new RestWikiPagePresenter();

        wikiPagesFacade.createSingleWikiPageFrom(input.title(), output);

        return output.getResponseEntity();
    }

    @PostMapping(path = "/body")
    public ResponseEntity<WikiPagesDto> createWikiPagesFromBody(@RequestBody WikiPageDto request) {
        var input = new CreateWikiPagesFromBodyInput(request);

        var output = new RestWikiPagesPresenter();

        wikiPagesFacade.createMultipleWikiPagesFrom(input.body(), output);

        return output.getResponseEntity();

    }

    @GetMapping
    public ResponseEntity<WikiPagesDto> fetchAllWikiPages() {
        var output = new RestWikiPagesSearchResultPresenter();

        wikiPagesFacade.findAllWikiPages(output);

        return output.getResponseEntity();
    }

}
