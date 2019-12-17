package com.zihler.wiki.adapters.presentation.rest.presenters;

import com.zihler.wiki.adapters.presentation.rest.dtos.WikiPagesDto;
import com.zihler.wiki.application.outbound_ports.documents.WikiPagesDocument;
import com.zihler.wiki.application.use_cases.find_wiki_pages.outbound_port.WikiPagesSearchResultPresenter;
import org.springframework.http.ResponseEntity;

public class RestWikiPagesSearchResultPresenter implements WikiPagesSearchResultPresenter {
    private ResponseEntity<WikiPagesDto> responseEntity;

    @Override
    public void present(WikiPagesDocument wikiPagesDocument) {
        responseEntity = ResponseEntity.ok(WikiPagesDto.from(wikiPagesDocument));
    }

    public ResponseEntity<WikiPagesDto> getResponseEntity() {
        return responseEntity;
    }
}
