package com.zihler.wiki.application.use_cases.create_wiki_pages;

import com.zihler.wiki.application.outbound_ports.gateway.FindWikiPageByReferenceTag;
import com.zihler.wiki.application.outbound_ports.gateway.StoreWikiPage;
import com.zihler.wiki.application.outbound_ports.presenter.Presenter;
import com.zihler.wiki.application.use_cases.create_wiki_pages.inbound_ports.CreateWikiPages;
import com.zihler.wiki.application.use_cases.create_wiki_pages.roles.CreatedWikiPages;
import com.zihler.wiki.domain.values.Body;
import com.zihler.wiki.domain.values.WikiPagesDocument;

public class CreateWikiPagesUseCase implements CreateWikiPages {
    private final FindWikiPageByReferenceTag findWikiPage;
    private final StoreWikiPage storeWikiPage;

    public CreateWikiPagesUseCase(FindWikiPageByReferenceTag findWikiPage, StoreWikiPage storeWikiPage) {
        this.findWikiPage = findWikiPage;
        this.storeWikiPage = storeWikiPage;
    }

    @Override
    public void from(Body body, Presenter<WikiPagesDocument> presenter) {
        CreatedWikiPages wikiPages = CreatedWikiPages.create(body, findWikiPage, storeWikiPage);

        wikiPages.storeAll();

        presenter.present(wikiPages.toDocument());
    }

}
