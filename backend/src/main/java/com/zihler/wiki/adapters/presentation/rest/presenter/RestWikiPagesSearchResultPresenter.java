package com.zihler.wiki.adapters.presentation.rest.presenter;

import com.zihler.wiki.adapters.presentation.rest.dtos.WikiPagesSearchResultResponse;
import com.zihler.wiki.application.use_cases.outbound_ports.Presenter;
import com.zihler.wiki.application.use_cases.outbound_ports.WikiPagesSearchResultDocument;

public class RestWikiPagesSearchResultPresenter
        extends RestPresenter<WikiPagesSearchResultResponse>
        implements Presenter<WikiPagesSearchResultDocument> {

    @Override
    public void present(WikiPagesSearchResultDocument wikiPagesSearchResultDocument) {
        setOkResponse(WikiPagesSearchResultResponse.from(wikiPagesSearchResultDocument));
    }
}
