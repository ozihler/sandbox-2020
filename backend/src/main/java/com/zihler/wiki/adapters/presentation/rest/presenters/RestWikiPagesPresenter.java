package com.zihler.wiki.adapters.presentation.rest.presenters;

import com.zihler.wiki.adapters.presentation.rest.dtos.WikiPagesDto;
import com.zihler.wiki.application.outbound_ports.documents.WikiPagesDocument;
import com.zihler.wiki.application.use_cases.view_all_wiki_pages.outbound_port.WikiPagesPresenter;
import org.springframework.http.ResponseEntity;

public class RestWikiPagesPresenter implements WikiPagesPresenter {
    private ResponseEntity<WikiPagesDto> responseEntity;

    @Override
    public void present(WikiPagesDocument wikiPagesDocument) {
        responseEntity = ResponseEntity.ok(WikiPagesDto.from(wikiPagesDocument));
    }

    public ResponseEntity<WikiPagesDto> getResponseEntity() {
        return responseEntity;
    }
}
