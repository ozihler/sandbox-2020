package com.zihler.wiki.application.use_cases.create_wiki_page.roles;

import com.zihler.wiki.application.outbound_ports.documents.WikiPageDocument;
import com.zihler.wiki.application.outbound_ports.gateway.StoreWikiPage;
import com.zihler.wiki.application.outbound_ports.presenter.Presenter;
import com.zihler.wiki.domain.entity.WikiPage;

public class CreatedWikiPage {

    private WikiPage self;
    private StoreWikiPage storeWikiPage;
    private Presenter<WikiPageDocument> presenter;

    public CreatedWikiPage(WikiPage wikiPage, StoreWikiPage storeWikiPage, Presenter<WikiPageDocument> presenter) {
        self = wikiPage;
        this.storeWikiPage = storeWikiPage;
        this.presenter = presenter;
    }

    public CreatedWikiPage store() {
        self = storeWikiPage.as(self);
        return this;
    }

    private WikiPageDocument asDocument() {
        return WikiPageDocument.of(self);
    }

    public void present() {
        presenter.present(asDocument());
    }
}
