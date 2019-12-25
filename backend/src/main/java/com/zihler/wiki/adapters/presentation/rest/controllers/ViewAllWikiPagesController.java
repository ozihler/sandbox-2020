package com.zihler.wiki.adapters.presentation.rest.controllers;

import com.zihler.wiki.application.outbound_ports.gateways.RetrieveAllWikiPages;
import com.zihler.wiki.application.use_cases.view_all_wiki_pages.ViewAllWikiPagesUseCase;
import com.zihler.wiki.application.use_cases.view_all_wiki_pages.inbound_port.ViewAllWikiPages;
import com.zihler.wiki.application.use_cases.view_all_wiki_pages.outbound_port.WikiPagesPresenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ViewAllWikiPagesController {

    private final ViewAllWikiPages viewAllWikiPages;

    @Autowired
    public ViewAllWikiPagesController(RetrieveAllWikiPages retrieveAllWikiPages) {
        viewAllWikiPages = new ViewAllWikiPagesUseCase(retrieveAllWikiPages);
    }

    public void viewAllWikiPages(WikiPagesPresenter output) {
        viewAllWikiPages.callWith(output);
    }
}
