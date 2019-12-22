package com.zihler.wiki.adapters.presentation.rest;

import com.zihler.wiki.adapters.presentation.rest.dtos.WikiPageDto;
import com.zihler.wiki.adapters.presentation.rest.dtos.WikiPagesDto;
import com.zihler.wiki.adapters.presentation.rest.inputs.CreateWikiPageFromTitleInput;
import com.zihler.wiki.adapters.presentation.rest.inputs.ReferenceTagInput;
import com.zihler.wiki.adapters.presentation.rest.inputs.WikiPagesInput;
import com.zihler.wiki.adapters.presentation.rest.presenters.RestWikiPagePresenter;
import com.zihler.wiki.adapters.presentation.rest.presenters.RestWikiPagesSearchResultPresenter;
import com.zihler.wiki.adapters.spring.SpringWikiPagesFacade;
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
    public ResponseEntity<WikiPageDto> extendWikiArticle(@RequestBody WikiPageDto request) {
        var input = new WikiPagesInput(request);

        var output = new RestWikiPagePresenter();

        wikiPagesFacade.extendWikiArticle(input.wikiPage(), output);

        return output.getResponseEntity();
    }

    @GetMapping
    public ResponseEntity<WikiPagesDto> fetchAllWikiPages() {
        var output = new RestWikiPagesSearchResultPresenter();

        wikiPagesFacade.findAllWikiPages(output);

        return output.getResponseEntity();
    }

    @GetMapping("/{referenceTag}")
    ResponseEntity<WikiPageDto> fetchWikiPageByReferenceTag(@PathVariable("referenceTag") String referenceTag) {
        var input = new ReferenceTagInput(referenceTag);

        var output = new RestWikiPagePresenter();

        wikiPagesFacade.fetchWikiPageByReferenceTag(input.referenceTag(), output);

        return output.getResponseEntity();
    }

}
