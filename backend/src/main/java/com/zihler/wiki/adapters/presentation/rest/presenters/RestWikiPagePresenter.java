package com.zihler.wiki.adapters.presentation.rest.presenters;

import com.zihler.wiki.adapters.presentation.rest.dtos.WikiPageDto;
import com.zihler.wiki.application.outbound_ports.documents.WikiPageDocument;
import com.zihler.wiki.application.outbound_ports.presenters.WikiPagePresenter;
import org.springframework.http.ResponseEntity;

public class RestWikiPagePresenter implements WikiPagePresenter {
    private ResponseEntity<WikiPageDto> response;

    @Override
    public void present(WikiPageDocument document) {
        response = ResponseEntity.ok(WikiPageDto.from(document));
    }

    public ResponseEntity<WikiPageDto> getResponseEntity() {
        return response;
    }
}
