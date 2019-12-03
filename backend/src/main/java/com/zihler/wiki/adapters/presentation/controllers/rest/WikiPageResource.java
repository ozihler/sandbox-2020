package com.zihler.wiki.adapters.presentation.controllers.rest;

import com.zihler.wiki.adapters.presentation.controllers.rest.dtos.CreateWikiPageFromTitleRequest;
import com.zihler.wiki.adapters.presentation.controllers.rest.dtos.WikiPageResponse;
import com.zihler.wiki.adapters.presentation.presenter.rest.CreateWikiPageFromTitlePresenter;
import com.zihler.wiki.domain.values.Title;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.zihler.wiki.application.use_cases.CreateWikiPageFromTitle.createWikiPage;

@CrossOrigin
@RestController
@RequestMapping(path = "api/sandbox/wiki/page", produces = "application/json")
public class WikiPageResource {

    @PostMapping(path = "/title")
    public ResponseEntity<WikiPageResponse> createWikiPageFromTitle(@RequestBody CreateWikiPageFromTitleRequest request) {
        Title title = Title.from(request.getTitle());

        CreateWikiPageFromTitlePresenter presenter = new CreateWikiPageFromTitlePresenter();

        createWikiPage().from(title, presenter);

        return presenter.getResponseEntity();
    }
}
