package com.zihler.wiki.application.use_cases.create_wiki_pages_from_body;

import com.zihler.wiki.application.outbound_ports.gateway.FindWikiPage;
import com.zihler.wiki.application.outbound_ports.gateway.StoreWikiPage;
import com.zihler.wiki.application.outbound_ports.presenter.WikiPagePresenter;
import com.zihler.wiki.application.use_cases.create_wiki_pages_from_body.context.CreateWikiPagesFromBodyUseCaseContext;
import com.zihler.wiki.application.use_cases.create_wiki_pages_from_body.inbound_ports.CreateWikiPagesFromBody;
import com.zihler.wiki.domain.values.Body;

public class CreateWikiPagesFromBodyFromBodyUseCase implements CreateWikiPagesFromBody {

    private final FindWikiPage findWikiPage;
    private final StoreWikiPage storeWikiPage;

    public CreateWikiPagesFromBodyFromBodyUseCase(FindWikiPage findWikiPage, StoreWikiPage storeWikiPage) {
        this.findWikiPage = findWikiPage;
        this.storeWikiPage = storeWikiPage;
    }

    @Override
    public void from(Body body, WikiPagePresenter presenter) {
        CreateWikiPagesFromBodyUseCaseContext
                .initialize(body, findWikiPage, storeWikiPage, presenter)
                .enactUseCase();
    }

}
