package com.zihler.wiki.adapters.presentation.presenter.rest;

import com.zihler.wiki.adapters.presentation.controllers.rest.dtos.WikiPageResponse;
import com.zihler.wiki.application.use_cases.ports.IPresentWikiPages;
import com.zihler.wiki.application.use_cases.ports.WikiPageDocument;
import org.springframework.http.ResponseEntity;

public class CreateWikiPageFromTitlePresenter implements IPresentWikiPages {
    private ResponseEntity<WikiPageResponse> response;

    public ResponseEntity<WikiPageResponse> getResponseEntity() {
        return response;
    }

    @Override
    public void present(WikiPageDocument document) {

    }
}
