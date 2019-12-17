package com.zihler.wiki.application.use_cases.create_wiki_pages_from_body;

import com.zihler.wiki.application.outbound_ports.documents.BodyDocument;
import com.zihler.wiki.application.outbound_ports.gateways.FindWikiPage;
import com.zihler.wiki.application.outbound_ports.gateways.StoreWikiPage;
import com.zihler.wiki.application.outbound_ports.presenters.WikiPagePresenter;
import com.zihler.wiki.application.use_cases.create_wiki_pages_from_body.context.CreateWikiPagesFromBodyUseCaseContext;
import com.zihler.wiki.application.use_cases.create_wiki_pages_from_body.inbound_port.CreateWikiPagesFromBody;

public class CreateWikiPagesFromBodyUseCase implements CreateWikiPagesFromBody {

    private final FindWikiPage findWikiPage;
    private final StoreWikiPage storeWikiPage;

    public CreateWikiPagesFromBodyUseCase(FindWikiPage findWikiPage, StoreWikiPage storeWikiPage) {
        this.findWikiPage = findWikiPage;
        this.storeWikiPage = storeWikiPage;
    }

    @Override
    public void callWith(BodyDocument body, WikiPagePresenter presenter) {
        CreateWikiPagesFromBodyUseCaseContext
                .initialize(body, findWikiPage, storeWikiPage, presenter)
                .enactUseCase();
    }

}
