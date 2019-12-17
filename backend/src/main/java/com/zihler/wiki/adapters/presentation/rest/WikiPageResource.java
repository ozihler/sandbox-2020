package com.zihler.wiki.adapters.presentation.rest;

import com.zihler.wiki.adapters.facades.SpringCreateWikiPageUseCaseFacade;
import com.zihler.wiki.adapters.facades.SpringCreateWikiPagesUseCaseFacade;
import com.zihler.wiki.adapters.facades.SpringFindAllWikiPagesUseCaseFacade;
import com.zihler.wiki.adapters.presentation.rest.dto.WikiPageDto;
import com.zihler.wiki.adapters.presentation.rest.dto.WikiPagesDto;
import com.zihler.wiki.adapters.presentation.rest.input.CreateWikiPageFromTitleInput;
import com.zihler.wiki.adapters.presentation.rest.input.CreateWikiPagesFromBodyInput;
import com.zihler.wiki.adapters.presentation.rest.presenter.RestWikiPagePresenter;
import com.zihler.wiki.adapters.presentation.rest.presenter.RestWikiPagesPresenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(path = "api/sandbox/wiki/pages", produces = "application/json")
public class WikiPageResource {
    private SpringCreateWikiPageUseCaseFacade createWikiPage;
    private SpringFindAllWikiPagesUseCaseFacade findAllWikiPages;
    private SpringCreateWikiPagesUseCaseFacade createWikiPages;

    @Autowired
    public WikiPageResource(
            SpringCreateWikiPageUseCaseFacade createWikiPage,
            SpringFindAllWikiPagesUseCaseFacade findAllWikiPages,
            SpringCreateWikiPagesUseCaseFacade createWikiPages) {
        this.createWikiPage = createWikiPage;
        this.findAllWikiPages = findAllWikiPages;
        this.createWikiPages = createWikiPages;
    }

    @PostMapping(path = "/title")
    public ResponseEntity<WikiPageDto> createWikiPageFromTitle(@RequestBody WikiPageDto request) {
        var input = new CreateWikiPageFromTitleInput(request);

        var output = new RestWikiPagePresenter();

        createWikiPage.from(input.title(), output);

        return output.getResponseEntity();
    }

    @PostMapping(path = "/body")
    public ResponseEntity<WikiPagesDto> createWikiPagesFromBody(@RequestBody WikiPageDto request) {
        var input = new CreateWikiPagesFromBodyInput(request);

        var output = new RestWikiPagesPresenter();

        createWikiPages.from(input.body(), output);

        return output.getResponseEntity();

    }

    @GetMapping
    public ResponseEntity<WikiPagesDto> fetchAllWikiPages() {
        var presenter = new RestWikiPagesPresenter();

        findAllWikiPages.callWith(presenter);

        return presenter.getResponseEntity();

    }

}
