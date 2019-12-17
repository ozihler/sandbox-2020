package com.zihler.wiki.application.use_cases.find_wiki_pages;

import com.zihler.wiki.application.outbound_ports.gateway.RetrieveAllWikiPages;
import com.zihler.wiki.application.outbound_ports.presenter.Presenter;
import com.zihler.wiki.application.use_cases.find_wiki_pages.inbound_ports.FindAllWikiPages;
import com.zihler.wiki.domain.values.WikiPagesDocument;

public class FindAllWikiPagesUseCase implements FindAllWikiPages {
    private RetrieveAllWikiPages retrieveAllWikiPages;

    public FindAllWikiPagesUseCase(RetrieveAllWikiPages retrieveAllWikiPages) {
        this.retrieveAllWikiPages = retrieveAllWikiPages;
    }

    @Override
    public void callWith(Presenter<WikiPagesDocument> presenter) {

        FindAllWikiPagesContext
                .initialize(retrieveAllWikiPages, presenter)
                .enactUseCase();
    }
}
