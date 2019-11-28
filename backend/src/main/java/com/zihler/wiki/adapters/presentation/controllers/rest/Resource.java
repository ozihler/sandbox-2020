package com.zihler.wiki.adapters.presentation.controllers.rest;

import com.zihler.wiki.adapters.presentation.presenter.rest.UpdateWikiPageBodyPresenter;
import com.zihler.wiki.application.use_cases.ports.WikiPageDocument;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.zihler.wiki.application.use_cases.UpdateWikiPageBody.updateWikiPageBody;

@RestController
@CrossOrigin
@RequestMapping(path = "/sandbox/wiki/page", produces = "application/json")
public class Resource {

    @PostMapping(path = "/body")
    public ResponseEntity<UpdateWikiPageBodyResponse> updateBody(UpdateWikiPageBodyRequest request) {
        WikiPageDocument wikiPage = WikiPageDocumentFactory.from(request);

        UpdateWikiPageBodyPresenter presenter = new UpdateWikiPageBodyPresenter();

        updateWikiPageBody().of(wikiPage, presenter);

        return presenter.getResponseEntity();
    }

}
