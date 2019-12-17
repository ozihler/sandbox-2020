package com.zihler.wiki.application.use_cases.create_wiki_page_from_title;

import com.zihler.wiki.application.outbound_ports.gateways.FindWikiPage;
import com.zihler.wiki.application.outbound_ports.gateways.StoreWikiPage;
import com.zihler.wiki.application.outbound_ports.presenters.WikiPagePresenter;
import com.zihler.wiki.application.use_cases.create_wiki_page_from_title.context.CreateWikiPageFromTitleUseCaseContext;
import com.zihler.wiki.application.use_cases.create_wiki_page_from_title.inbound_port.CreateWikiPageFromTitle;
import com.zihler.wiki.domain.values.Title;

public class CreateWikiPageFromTitleUseCase implements CreateWikiPageFromTitle {
    private final FindWikiPage findWikiPage;
    private final StoreWikiPage storeWikiPage;

    public CreateWikiPageFromTitleUseCase(FindWikiPage findWikiPage, StoreWikiPage storeWikiPage) {
        this.findWikiPage = findWikiPage;
        this.storeWikiPage = storeWikiPage;
    }

    @Override
    public void callWith(Title title, WikiPagePresenter presenter) {
        CreateWikiPageFromTitleUseCaseContext
                .initialize(title, findWikiPage, storeWikiPage, presenter)
                .enactUseCase();
    }


}
