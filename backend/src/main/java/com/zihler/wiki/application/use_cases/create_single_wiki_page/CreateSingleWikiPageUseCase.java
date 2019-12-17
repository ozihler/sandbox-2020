package com.zihler.wiki.application.use_cases.create_single_wiki_page;

import com.zihler.wiki.application.outbound_ports.gateway.FindWikiPage;
import com.zihler.wiki.application.outbound_ports.gateway.StoreWikiPage;
import com.zihler.wiki.application.use_cases.create_single_wiki_page.context.CreateSingleWikiPageUseCaseContext;
import com.zihler.wiki.application.use_cases.create_single_wiki_page.inbound_ports.CreateSingleWikiPage;
import com.zihler.wiki.application.use_cases.create_single_wiki_page.outbound_port.presenter.SingleWikiPagePresenter;
import com.zihler.wiki.domain.values.Title;

public class CreateSingleWikiPageUseCase implements CreateSingleWikiPage {
    private final FindWikiPage findWikiPage;
    private final StoreWikiPage storeWikiPage;

    public CreateSingleWikiPageUseCase(FindWikiPage findWikiPage, StoreWikiPage storeWikiPage) {
        this.findWikiPage = findWikiPage;
        this.storeWikiPage = storeWikiPage;
    }

    @Override
    public void from(Title title, SingleWikiPagePresenter presenter) {
        CreateSingleWikiPageUseCaseContext.initialize(title, findWikiPage, storeWikiPage, presenter)
                .enactUseCase();
    }

}
