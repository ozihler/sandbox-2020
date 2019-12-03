package com.zihler.wiki.adapters.presentation.rest.presenter;

import com.zihler.wiki.adapters.presentation.rest.dtos.WikiPageResponse;
import com.zihler.wiki.application.use_cases.ports.WikiPageDocument;
import com.zihler.wiki.application.use_cases.ports.WikiPagePresenter;
import org.springframework.http.ResponseEntity;

public class RestWikiPagePresenter implements WikiPagePresenter {
    private ResponseEntity<WikiPageResponse> response;

    @Override
    public void present(WikiPageDocument document) {
        this.response = ResponseEntity.ok(WikiPageResponse.from(document));
    }

    public ResponseEntity<WikiPageResponse> getResponseEntity() {
        return response;
    }
}
