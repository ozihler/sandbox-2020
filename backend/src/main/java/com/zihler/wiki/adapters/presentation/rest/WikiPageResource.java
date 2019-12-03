package com.zihler.wiki.adapters.presentation.rest;

import com.zihler.wiki.adapters.presentation.rest.dtos.CreateWikiPageFromTitleRequest;
import com.zihler.wiki.adapters.presentation.rest.dtos.WikiPageResponse;
import com.zihler.wiki.adapters.presentation.rest.dtos.WikiPagesSearchResultResponse;
import com.zihler.wiki.adapters.presentation.rest.facade.SpringCreateWikiPageFromTitleUseCaseFacade;
import com.zihler.wiki.adapters.presentation.rest.facade.SpringFindWikiPagesUseCaseFacade;
import com.zihler.wiki.adapters.presentation.rest.presenter.RestWikiPagePresenter;
import com.zihler.wiki.adapters.presentation.rest.presenter.RestWikiPagesSearchResultPresenter;
import com.zihler.wiki.domain.values.Title;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(path = "api/sandbox/wiki/pages", produces = "application/json")
public class WikiPageResource {
    private SpringCreateWikiPageFromTitleUseCaseFacade createWikiPage;
    private SpringFindWikiPagesUseCaseFacade findWikiPages;

    @Autowired
    public WikiPageResource(
            SpringCreateWikiPageFromTitleUseCaseFacade createWikiPage,
            SpringFindWikiPagesUseCaseFacade findWikiPages) {
        this.createWikiPage = createWikiPage;
        this.findWikiPages = findWikiPages;
    }

    @PostMapping(path = "/title")
    public ResponseEntity<WikiPageResponse> createWikiPageFromTitle(@RequestBody CreateWikiPageFromTitleRequest request) {
        Title title = Title.from(request.getTitle());

        RestWikiPagePresenter presenter = new RestWikiPagePresenter();

        this.createWikiPage.from(title, presenter);

        return presenter.getResponseEntity();
    }

    @GetMapping
    public ResponseEntity<WikiPagesSearchResultResponse> fetchAllWikiPages() {
        RestWikiPagesSearchResultPresenter presenter = new RestWikiPagesSearchResultPresenter();

        this.findWikiPages.all(presenter);

        return presenter.getResponseEntity();
    }

}
