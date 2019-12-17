package com.zihler.wiki.adapters.presentation.rest.presenter;

import com.zihler.wiki.adapters.presentation.rest.dto.WikiPageDto;
import com.zihler.wiki.application.outbound_ports.documents.WikiPageDocument;
import com.zihler.wiki.application.outbound_ports.presenter.WikiPagePresenter;
import org.springframework.http.ResponseEntity;

public class RestWikiPagePresenter
        extends RestPresenter<WikiPageDto>
        implements WikiPagePresenter {

    @Override
    public void present(WikiPageDocument document) {
        response = ResponseEntity.ok(WikiPageDto.from(document));
    }
}
