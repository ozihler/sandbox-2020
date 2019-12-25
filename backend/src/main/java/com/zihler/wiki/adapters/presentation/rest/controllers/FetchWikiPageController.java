package com.zihler.wiki.adapters.presentation.rest.controllers;

import com.zihler.wiki.adapters.presentation.rest.controllers.inputs.ReferenceTagInput;
import com.zihler.wiki.application.outbound_ports.gateways.FindWikiPage;
import com.zihler.wiki.application.outbound_ports.presenters.WikiPagePresenter;
import com.zihler.wiki.application.use_cases.fetch_wiki_page.FetchWikiPageUseCase;
import com.zihler.wiki.application.use_cases.fetch_wiki_page.inbound_port.FetchWikiPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FetchWikiPageController {
    private final FetchWikiPage fetchWikiPage;

    @Autowired
    public FetchWikiPageController(FindWikiPage findWikiPage) {
        fetchWikiPage = new FetchWikiPageUseCase(findWikiPage);
    }

    public void fetchBy(String referenceTag, WikiPagePresenter output) {
        var input = new ReferenceTagInput(referenceTag);

        fetchWikiPage.byReferenceTag(input.referenceTag(), output);
    }
}
