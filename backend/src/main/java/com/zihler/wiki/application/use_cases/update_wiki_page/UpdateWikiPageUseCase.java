package com.zihler.wiki.application.use_cases.update_wiki_page;

import com.zihler.wiki.application.outbound_ports.documents.WikiPageDocument;
import com.zihler.wiki.application.outbound_ports.gateways.FindWikiPage;
import com.zihler.wiki.application.outbound_ports.gateways.StoreWikiPage;
import com.zihler.wiki.application.outbound_ports.presenters.WikiPagePresenter;
import com.zihler.wiki.application.use_cases.update_wiki_page.context.UpdateWikiPageUseCaseContext;
import com.zihler.wiki.application.use_cases.update_wiki_page.inbound_port.UpdateWikiPage;

public class UpdateWikiPageUseCase implements UpdateWikiPage {
    private final FindWikiPage findWikiPages;
    private final StoreWikiPage storeWikiPage;

    public UpdateWikiPageUseCase(FindWikiPage findWikiPages, StoreWikiPage storeWikiPage) {
        this.findWikiPages = findWikiPages;
        this.storeWikiPage = storeWikiPage;
    }

    @Override
    public void callWith(WikiPageDocument wikiPage, WikiPagePresenter output) {
        UpdateWikiPageUseCaseContext
                .initialize(wikiPage, findWikiPages, storeWikiPage, output)
                .enactUseCase();
    }
}
