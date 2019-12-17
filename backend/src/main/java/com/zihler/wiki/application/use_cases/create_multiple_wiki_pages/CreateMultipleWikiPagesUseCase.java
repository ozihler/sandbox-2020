package com.zihler.wiki.application.use_cases.create_multiple_wiki_pages;

import com.zihler.wiki.application.outbound_ports.gateway.FindWikiPage;
import com.zihler.wiki.application.outbound_ports.gateway.StoreWikiPage;
import com.zihler.wiki.application.outbound_ports.presenter.WikiPagePresenter;
import com.zihler.wiki.application.use_cases.create_multiple_wiki_pages.context.CreateMultipleWikiPagesUseCaseContext;
import com.zihler.wiki.application.use_cases.create_multiple_wiki_pages.inbound_ports.CreateMultipleWikiPages;
import com.zihler.wiki.domain.values.Body;

public class CreateMultipleWikiPagesUseCase implements CreateMultipleWikiPages {

    private final FindWikiPage findWikiPage;
    private final StoreWikiPage storeWikiPage;

    public CreateMultipleWikiPagesUseCase(FindWikiPage findWikiPage, StoreWikiPage storeWikiPage) {
        this.findWikiPage = findWikiPage;
        this.storeWikiPage = storeWikiPage;
    }

    @Override
    public void from(Body body, WikiPagePresenter presenter) {
        CreateMultipleWikiPagesUseCaseContext
                .initialize(body, findWikiPage, storeWikiPage, presenter)
                .enactUseCase();
    }

}
