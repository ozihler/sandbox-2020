package com.zihler.wiki.adapters.presentation.rest.presenter;

import com.zihler.wiki.adapters.presentation.rest.dtos.WikiPageResponse;
import com.zihler.wiki.application.use_cases.outbound_ports.Presenter;
import com.zihler.wiki.application.use_cases.outbound_ports.WikiPageDocument;
import org.springframework.http.ResponseEntity;

public class RestWikiPagePresenter
        extends RestPresenter<WikiPageResponse>
        implements Presenter<WikiPageDocument> {

    @Override
    public void present(WikiPageDocument document) {
        this.response = ResponseEntity.ok(WikiPageResponse.from(document));
    }
}
