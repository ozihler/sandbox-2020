package com.zihler.wiki.application.habits.create_wiki_page.roles;

import com.zihler.wiki.application.outbound_ports.documents.WikiPageDocument;
import com.zihler.wiki.application.outbound_ports.gateway.StoreWikiPage;
import com.zihler.wiki.application.outbound_ports.presenter.WikiPagePresenter;
import com.zihler.wiki.domain.entity.WikiPage;

public class CreatedWikiPage {

    private WikiPage self;
    private StoreWikiPage storeWikiPage;
    private WikiPagePresenter presenter;

    public CreatedWikiPage(WikiPage wikiPage, StoreWikiPage storeWikiPage, WikiPagePresenter presenter) {
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
