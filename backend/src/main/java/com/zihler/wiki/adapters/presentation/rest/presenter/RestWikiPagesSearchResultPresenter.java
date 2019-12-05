package com.zihler.wiki.adapters.presentation.rest.presenter;

import com.zihler.wiki.adapters.presentation.rest.dto.WikiPagesSearchResultResponse;
import com.zihler.wiki.application.use_cases.outbound_ports.documents.WikiPagesSearchResultDocument;
import com.zihler.wiki.application.use_cases.outbound_ports.presenter.Presenter;

public class RestWikiPagesSearchResultPresenter
        extends RestPresenter<WikiPagesSearchResultResponse>
        implements Presenter<WikiPagesSearchResultDocument> {

    @Override
    public void present(WikiPagesSearchResultDocument wikiPagesSearchResultDocument) {
        setOkResponse(WikiPagesSearchResultResponse.from(wikiPagesSearchResultDocument));
    }
}
