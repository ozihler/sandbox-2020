package com.zihler.wiki.application.use_cases.find_wiki_pages;

import com.zihler.wiki.application.outbound_ports.gateways.RetrieveAllWikiPages;
import com.zihler.wiki.application.use_cases.find_wiki_pages.context.FindAllWikiPagesUseCaseContext;
import com.zihler.wiki.application.use_cases.find_wiki_pages.inbound_port.FindAllWikiPages;
import com.zihler.wiki.application.use_cases.find_wiki_pages.outbound_port.WikiPagesSearchResultPresenter;

public class FindAllWikiPagesUseCase implements FindAllWikiPages {
    private RetrieveAllWikiPages retrieveAllWikiPages;

    public FindAllWikiPagesUseCase(RetrieveAllWikiPages retrieveAllWikiPages) {
        this.retrieveAllWikiPages = retrieveAllWikiPages;
    }

    @Override
    public void andSendThemTo(WikiPagesSearchResultPresenter output) {
        FindAllWikiPagesUseCaseContext
                .initialize(retrieveAllWikiPages, output)
                .enactUseCase();
    }
}
