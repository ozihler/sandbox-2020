package com.zihler.wiki.adapters.presentation.controllers.rest;

import com.zihler.wiki.adapters.presentation.controllers.rest.dtos.CreateWikiPageFromTitleRequest;
import com.zihler.wiki.adapters.presentation.controllers.rest.dtos.UpdateWikiPageBodyRequest;
import com.zihler.wiki.adapters.presentation.controllers.rest.dtos.WikiPageResponse;
import com.zihler.wiki.adapters.presentation.presenter.rest.CreateWikiPageFromTitlePresenter;
import com.zihler.wiki.adapters.presentation.presenter.rest.UpdateWikiPageBodyPresenter;
import com.zihler.wiki.application.use_cases.ports.WikiPageDocument;
import com.zihler.wiki.domain.values.Title;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.zihler.wiki.application.use_cases.CreateWikiPageFromTitle.createWikiPage;
import static com.zihler.wiki.application.use_cases.UpdateWikiPageBody.updateWikiPageBody;

@RestController
@CrossOrigin
@RequestMapping(path = "/sandbox/wiki/page", produces = "application/json")
public class Resource {

    @PostMapping(path = "/title")
    public ResponseEntity<WikiPageResponse> createWikiPageFromTitle(CreateWikiPageFromTitleRequest request) {
        Title title = Title.from(request.getTitle());

        CreateWikiPageFromTitlePresenter presenter = new CreateWikiPageFromTitlePresenter();

        createWikiPage().from(title, presenter);

        return presenter.getResponseEntity();
    }

    @PostMapping(path = "/body")
    public ResponseEntity<WikiPageResponse> updateBody(UpdateWikiPageBodyRequest request) {
        WikiPageDocument wikiPage = WikiPageDocumentFactory.from(request);

        UpdateWikiPageBodyPresenter response = new UpdateWikiPageBodyPresenter();

        updateWikiPageBody().of(wikiPage, response);

        return response.asResponseEntity();
    }

}
