package com.zihler.wiki.application.use_cases.find_wiki_pages;

import com.zihler.wiki.application.outbound_ports.gateway.RetrieveAllWikiPages;
import com.zihler.wiki.application.use_cases.create_multiple_wiki_pages.outbound_port.presenter.MultipleWikiPagesPresenter;
import com.zihler.wiki.application.use_cases.find_wiki_pages.inbound_ports.FindAllWikiPages;

public class FindAllWikiPagesUseCase implements FindAllWikiPages {
    private RetrieveAllWikiPages retrieveAllWikiPages;

    public FindAllWikiPagesUseCase(RetrieveAllWikiPages retrieveAllWikiPages) {
        this.retrieveAllWikiPages = retrieveAllWikiPages;
    }

    @Override
    public void callWith(MultipleWikiPagesPresenter presenter) {

        FindAllWikiPagesUseCaseContext
                .initialize(retrieveAllWikiPages, presenter)
                .enactUseCase();
    }
}
