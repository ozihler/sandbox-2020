package com.zihler.wiki.adapters.presentation.rest.presenter;

import com.zihler.wiki.adapters.presentation.rest.dto.WikiPagesDto;
import com.zihler.wiki.application.outbound_ports.documents.WikiPageDocument;
import com.zihler.wiki.application.outbound_ports.documents.WikiPagesDocument;
import com.zihler.wiki.application.outbound_ports.presenter.WikiPagePresenter;
import org.springframework.http.ResponseEntity;

public class RestWikiPagesPresenter implements WikiPagePresenter {
    private WikiPagesDocument wikiPagesDocument = new WikiPagesDocument();

    @Override
    public void present(WikiPageDocument wikiPageDocument) {
        wikiPagesDocument.add(wikiPageDocument);
    }

    public ResponseEntity<WikiPagesDto> getResponseEntity() {
        return ResponseEntity.ok(WikiPagesDto.from(wikiPagesDocument));
    }
}
