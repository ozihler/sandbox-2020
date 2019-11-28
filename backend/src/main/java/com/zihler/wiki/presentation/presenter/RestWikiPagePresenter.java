package com.zihler.wiki.presentation.presenter;

import com.zihler.wiki.domain.values.WikiPageDocument;
import com.zihler.wiki.use_case.outbound_ports.WikiPagePresenter;
import org.springframework.http.ResponseEntity;

public class RestWikiPagePresenter implements WikiPagePresenter {

    private ResponseEntity<WikiPageDocument> response;

    @Override
    public void present(WikiPageDocument document) {
        response = ResponseEntity.ok(document);
    }

    public ResponseEntity<WikiPageDocument> getResponseEntity() {
        return response;
    }
}
