package com.zihler.wiki.adapters.presentation.rest.controllers;

import com.zihler.wiki.adapters.presentation.rest.inputs.TitleInput;
import com.zihler.wiki.application.outbound_ports.gateways.FindWikiPage;
import com.zihler.wiki.application.outbound_ports.gateways.StoreWikiPage;
import com.zihler.wiki.application.outbound_ports.presenters.WikiPagePresenter;
import com.zihler.wiki.application.use_cases.create_wiki_page_from_title.CreateWikiPageUseCase;
import com.zihler.wiki.application.use_cases.create_wiki_page_from_title.inbound_port.CreateWikiPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateWikiPageController {
    private CreateWikiPage createWikiPage;

    @Autowired
    public CreateWikiPageController(FindWikiPage findWikiPage, StoreWikiPage storeWikiPage) {
        createWikiPage = new CreateWikiPageUseCase(findWikiPage, storeWikiPage);
    }

    public void createWikiPage(String titleFromRequest, WikiPagePresenter output) {
        var input = new TitleInput(titleFromRequest);

        createWikiPage.callWith(input.title(), output);
    }
}
