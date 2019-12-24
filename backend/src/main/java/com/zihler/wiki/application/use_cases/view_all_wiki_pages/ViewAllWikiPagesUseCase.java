package com.zihler.wiki.application.use_cases.view_all_wiki_pages;

import com.zihler.wiki.application.outbound_ports.gateways.RetrieveAllWikiPages;
import com.zihler.wiki.application.use_cases.view_all_wiki_pages.context.ViewAllWikiPagesUseCaseContext;
import com.zihler.wiki.application.use_cases.view_all_wiki_pages.inbound_port.ViewAllWikiPages;
import com.zihler.wiki.application.use_cases.view_all_wiki_pages.outbound_port.WikiPagesPresenter;

public class ViewAllWikiPagesUseCase implements ViewAllWikiPages {
    private RetrieveAllWikiPages retrieveAllWikiPages;

    public ViewAllWikiPagesUseCase(RetrieveAllWikiPages retrieveAllWikiPages) {
        this.retrieveAllWikiPages = retrieveAllWikiPages;
    }

    @Override
    public void callWith(WikiPagesPresenter output) {
        ViewAllWikiPagesUseCaseContext
                .initialize(retrieveAllWikiPages, output)
                .enactUseCase();
    }
}
