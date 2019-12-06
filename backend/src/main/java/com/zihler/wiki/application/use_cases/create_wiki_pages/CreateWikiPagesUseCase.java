package com.zihler.wiki.application.use_cases.create_wiki_pages;

import com.zihler.wiki.application.use_cases.outbound_ports.gateway.FindWikiPage;
import com.zihler.wiki.application.use_cases.outbound_ports.gateway.StoreWikiPage;
import com.zihler.wiki.application.use_cases.outbound_ports.presenter.Presenter;
import com.zihler.wiki.application.use_cases.ports.CreateWikiPages;
import com.zihler.wiki.domain.values.Body;
import com.zihler.wiki.domain.values.ReferenceTag;
import com.zihler.wiki.domain.values.WikiPagesDocument;

public class CreateWikiPagesUseCase implements CreateWikiPages {
    private final FindWikiPage<ReferenceTag> findWikiPage;
    private final StoreWikiPage storeWikiPage;

    public CreateWikiPagesUseCase(FindWikiPage<ReferenceTag> findWikiPage, StoreWikiPage storeWikiPage) {
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
