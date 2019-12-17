package com.zihler.wiki.adapters.presentation.rest.presenter;

import com.zihler.wiki.adapters.presentation.rest.dto.WikiPagesDto;
import com.zihler.wiki.application.use_cases.find_wiki_pages.outbound_port.WikiPagesSearchResultPresenter;
import com.zihler.wiki.domain.values.WikiPagesDocument;
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
