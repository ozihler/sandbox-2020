package com.zihler.wiki.adapters.presentation.rest.presenter;

import com.zihler.wiki.adapters.presentation.rest.dto.WikiPagesDto;
import com.zihler.wiki.application.outbound_ports.documents.WikiPageDocument;
import com.zihler.wiki.application.use_cases.create_multiple_wiki_pages.outbound_port.presenter.MultipleWikiPagesPresenter;
import com.zihler.wiki.domain.values.WikiPagesDocument;

public class RestWikiPagesPresenter extends RestPresenter<WikiPagesDto> implements MultipleWikiPagesPresenter {
    @Override
    public void present(WikiPagesDocument wikiPagesDocument) {
        setOkResponse(WikiPagesDto.from(wikiPagesDocument));
    }

    @Override
    public void present(WikiPageDocument wikiPageDocument) {

    }
}
