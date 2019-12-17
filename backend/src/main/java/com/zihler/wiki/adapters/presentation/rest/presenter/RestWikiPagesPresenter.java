package com.zihler.wiki.adapters.presentation.rest.presenter;

import com.zihler.wiki.adapters.presentation.rest.dto.WikiPagesDto;
import com.zihler.wiki.application.outbound_ports.presenter.Presenter;
import com.zihler.wiki.domain.values.WikiPagesDocument;

public class RestWikiPagesPresenter extends RestPresenter<WikiPagesDto> implements Presenter<WikiPagesDocument> {
    @Override
    public void present(WikiPagesDocument wikiPagesDocument) {
        setOkResponse(WikiPagesDto.from(wikiPagesDocument));
    }
}
