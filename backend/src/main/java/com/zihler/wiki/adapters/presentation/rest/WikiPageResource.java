package com.zihler.wiki.adapters.presentation.rest;

import com.zihler.wiki.adapters.presentation.rest.dtos.CreateWikiPageFromTitleRequest;
import com.zihler.wiki.adapters.presentation.rest.dtos.WikiPageResponse;
import com.zihler.wiki.adapters.presentation.rest.facade.SpringCreateWikiPageFromTitleUseCaseFacade;
import com.zihler.wiki.adapters.presentation.rest.presenter.RestWikiPagePresenter;
import com.zihler.wiki.domain.values.Title;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(path = "api/sandbox/wiki/page", produces = "application/json")
public class WikiPageResource {
    private SpringCreateWikiPageFromTitleUseCaseFacade createWikiPage;

    @Autowired
    public WikiPageResource(SpringCreateWikiPageFromTitleUseCaseFacade createWikiPage) {
        this.createWikiPage = createWikiPage;
    }

    @PostMapping(path = "/title")
    public ResponseEntity<WikiPageResponse> createWikiPageFromTitle(@RequestBody CreateWikiPageFromTitleRequest request) {
        Title title = Title.from(request.getTitle());

        RestWikiPagePresenter presenter = new RestWikiPagePresenter();

        this.createWikiPage.from(title, presenter);

        return presenter.getResponseEntity();
    }
}
