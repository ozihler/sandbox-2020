package com.zihler.wiki.adapters.presentation.rest.presenter;

import com.zihler.wiki.adapters.presentation.rest.dtos.WikiPagesResponse;
import com.zihler.wiki.application.use_cases.outbound_ports.Presenter;
import com.zihler.wiki.domain.values.WikiPagesDocument;

public class RestWikiPagesPresenter extends RestPresenter<WikiPagesResponse> implements Presenter<WikiPagesDocument> {
    @Override
    public void present(WikiPagesDocument wikiPagesDocument) {
        setOkResponse(WikiPagesResponse.from(wikiPagesDocument));
    }
}
