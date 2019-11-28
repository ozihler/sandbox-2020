package com.zihler.wiki.adapters.presentation.presenter.rest;

import com.zihler.wiki.adapters.presentation.controllers.rest.UpdateWikiPageBodyResponse;
import com.zihler.wiki.application.use_cases.ports.WikiPageDocument;
import com.zihler.wiki.application.use_cases.ports.WikiPagePresenter;
import org.springframework.http.ResponseEntity;

public class UpdateWikiPageBodyPresenter implements WikiPagePresenter {

    private ResponseEntity<UpdateWikiPageBodyResponse> response;

    @Override
    public void present(WikiPageDocument document) {
        response = ResponseEntity.ok(UpdateWikiPageBodyResponse.from(document));
    }

    public ResponseEntity<UpdateWikiPageBodyResponse> getResponseEntity() {
        return ResponseEntity.ok(new UpdateWikiPageBodyResponse());
    }
}
