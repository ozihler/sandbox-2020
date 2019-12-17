package com.zihler.wiki.application.use_cases.create_wiki_page_from_title.roles;

import com.zihler.wiki.application.outbound_ports.documents.WikiPageDocument;
import com.zihler.wiki.application.outbound_ports.gateway.FindWikiPage;
import com.zihler.wiki.application.outbound_ports.gateway.StoreWikiPage;
import com.zihler.wiki.application.outbound_ports.presenter.WikiPagePresenter;
import com.zihler.wiki.domain.entity.WikiPage;

public class CreatedWikiPage {

    private WikiPage self;
    private StoreWikiPage storeWikiPage;
    private WikiPagePresenter presenter;

    private CreatedWikiPage(WikiPage wikiPage, StoreWikiPage storeWikiPage, WikiPagePresenter presenter) {
        self = wikiPage;
        this.storeWikiPage = storeWikiPage;
        this.presenter = presenter;
    }

    public static CreatedWikiPage from(WikiPageDocument intendedWikiPage, StoreWikiPage storeWikiPage, WikiPagePresenter presenter, FindWikiPage findWikiPage) {
        WikiPage wikiPage = findOrCreateWikiPage(intendedWikiPage, findWikiPage);

        return new CreatedWikiPage(wikiPage, storeWikiPage, presenter);
    }


    private static WikiPage findOrCreateWikiPage(WikiPageDocument intendedWikiPage, FindWikiPage findWikiPage) {
        return findWikiPage.by(intendedWikiPage.getReferenceTag())
                .orElse(WikiPage.from(intendedWikiPage.getReferenceTag(), intendedWikiPage.getTitle()));
    }


    public void store() {
        self = storeWikiPage.as(self);
    }

    private WikiPageDocument asDocument() {
        return WikiPageDocument.of(self);
    }

    public void present() {
        presenter.present(asDocument());
    }
}
