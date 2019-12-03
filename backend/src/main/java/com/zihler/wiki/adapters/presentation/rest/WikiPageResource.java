package com.zihler.wiki.adapters.presentation.rest;

import com.zihler.wiki.adapters.facades.SpringCreateWikiPageFromTitleUseCaseFacade;
import com.zihler.wiki.adapters.facades.SpringCreateWikiPagesFromBodyUseCaseFacade;
import com.zihler.wiki.adapters.facades.SpringFindWikiPagesUseCaseFacade;
import com.zihler.wiki.adapters.presentation.rest.dtos.*;
import com.zihler.wiki.adapters.presentation.rest.presenter.RestWikiPagePresenter;
import com.zihler.wiki.adapters.presentation.rest.presenter.RestWikiPagesPresenter;
import com.zihler.wiki.adapters.presentation.rest.presenter.RestWikiPagesSearchResultPresenter;
import com.zihler.wiki.domain.values.Body;
import com.zihler.wiki.domain.values.Title;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(path = "api/sandbox/wiki/pages", produces = "application/json")
public class WikiPageResource {
    private SpringCreateWikiPageFromTitleUseCaseFacade createWikiPageFromTitle;
    private SpringFindWikiPagesUseCaseFacade findWikiPages;
    private SpringCreateWikiPagesFromBodyUseCaseFacade createWikiPagesFromBody;

    @Autowired
    public WikiPageResource(
            SpringCreateWikiPageFromTitleUseCaseFacade createWikiPageFromTitle,
            SpringFindWikiPagesUseCaseFacade findWikiPages,
            SpringCreateWikiPagesFromBodyUseCaseFacade createWikiPagesFromBody) {
        this.createWikiPageFromTitle = createWikiPageFromTitle;
        this.findWikiPages = findWikiPages;
        this.createWikiPagesFromBody = createWikiPagesFromBody;
    }

    @PostMapping(path = "/title")
    public ResponseEntity<WikiPageResponse> createWikiPageFromTitle(@RequestBody CreateWikiPageFromTitleRequest request) {
        Title title = Title.from(request.getTitle());

        RestWikiPagePresenter presenter = new RestWikiPagePresenter();

        this.createWikiPageFromTitle.from(title, presenter);

        return presenter.getResponseEntity();
    }

    @PostMapping(path = "/body")
    public ResponseEntity<WikiPagesResponse> createWikiPagesFromBody(@RequestBody CreateWikiPagesFromBodyRequest request) {
        Body body = Body.from(request.getBody());

        RestWikiPagesPresenter presenter = new RestWikiPagesPresenter();

        this.createWikiPagesFromBody.from(body, presenter);

        return presenter.getResponseEntity();
    }

    @GetMapping
    public ResponseEntity<WikiPagesSearchResultResponse> fetchAllWikiPages() {
        RestWikiPagesSearchResultPresenter presenter = new RestWikiPagesSearchResultPresenter();

        this.findWikiPages.all(presenter);

        return presenter.getResponseEntity();
    }

}
