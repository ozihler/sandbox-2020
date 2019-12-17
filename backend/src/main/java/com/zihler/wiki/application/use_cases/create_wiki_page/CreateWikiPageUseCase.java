package com.zihler.wiki.application.use_cases.create_wiki_page;

import com.zihler.wiki.application.outbound_ports.documents.WikiPageDocument;
import com.zihler.wiki.application.outbound_ports.gateway.FindWikiPageByTitle;
import com.zihler.wiki.application.outbound_ports.gateway.StoreWikiPage;
import com.zihler.wiki.application.outbound_ports.presenter.Presenter;
import com.zihler.wiki.application.use_cases.create_wiki_page.context.CreateWikiPageContext;
import com.zihler.wiki.application.use_cases.create_wiki_page.inbound_ports.CreateWikiPage;
import com.zihler.wiki.domain.values.Title;

public class CreateWikiPageUseCase implements CreateWikiPage {
    private final FindWikiPageByTitle findWikiPage;
    private final StoreWikiPage storeWikiPage;

    public CreateWikiPageUseCase(FindWikiPageByTitle findWikiPage, StoreWikiPage storeWikiPage) {
        this.findWikiPage = findWikiPage;
        this.storeWikiPage = storeWikiPage;
    }

    @Override
    public void from(Title title, Presenter<WikiPageDocument> presenter) {
        CreateWikiPageContext.initialize(title, findWikiPage, storeWikiPage, presenter)
                .enactUseCase();
    }

}
