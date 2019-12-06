package com.zihler.wiki.application.use_cases.create_wiki_pages;

import com.zihler.wiki.application.use_cases.outbound_ports.documents.WikiPageDocument;
import com.zihler.wiki.application.use_cases.outbound_ports.gateway.FindWikiPage;
import com.zihler.wiki.application.use_cases.outbound_ports.gateway.StoreWikiPage;
import com.zihler.wiki.application.use_cases.outbound_ports.presenter.Presenter;
import com.zihler.wiki.application.use_cases.ports.CreateWikiPage;
import com.zihler.wiki.domain.values.Title;

public class CreateWikiPageUseCase implements CreateWikiPage {
    private final FindWikiPage<Title> findWikiPage;
    private final StoreWikiPage storeWikiPage;

    public CreateWikiPageUseCase(FindWikiPage<Title> findWikiPage, StoreWikiPage storeWikiPage) {
        this.findWikiPage = findWikiPage;
        this.storeWikiPage = storeWikiPage;
    }

    @Override
    public void from(Title title, Presenter<WikiPageDocument> presenter) {

        CreatedWikiPage wikiPage = CreatedWikiPage.create(title, findWikiPage, storeWikiPage);

        wikiPage.store();

        presenter.present(wikiPage.asDocument());

    }

}
