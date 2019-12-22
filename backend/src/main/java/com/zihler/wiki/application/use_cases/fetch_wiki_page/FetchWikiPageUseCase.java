package com.zihler.wiki.application.use_cases.fetch_wiki_page;

import com.zihler.wiki.application.outbound_ports.gateways.FindWikiPage;
import com.zihler.wiki.application.outbound_ports.presenters.WikiPagePresenter;
import com.zihler.wiki.application.use_cases.fetch_wiki_page.context.FetchWikiPageUseCaseContext;
import com.zihler.wiki.application.use_cases.fetch_wiki_page.inbound_port.FetchWikiPage;
import com.zihler.wiki.domain.values.ReferenceTag;

public class FetchWikiPageUseCase implements FetchWikiPage {
    private FindWikiPage findWikiPage;

    public FetchWikiPageUseCase(FindWikiPage findWikiPage) {
        this.findWikiPage = findWikiPage;
    }

    @Override
    public void byReferenceTag(ReferenceTag referenceTag, WikiPagePresenter output) {
        FetchWikiPageUseCaseContext
                .initialize(referenceTag, findWikiPage, output)
                .enactUseCase();
    }
}
