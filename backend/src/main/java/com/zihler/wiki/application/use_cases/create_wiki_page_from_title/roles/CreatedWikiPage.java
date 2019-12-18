package com.zihler.wiki.application.use_cases.create_wiki_page_from_title.roles;

import com.zihler.wiki.application.outbound_ports.documents.WikiPageDocument;
import com.zihler.wiki.application.outbound_ports.gateways.FindWikiPage;
import com.zihler.wiki.application.outbound_ports.gateways.StoreWikiPage;
import com.zihler.wiki.application.outbound_ports.presenters.WikiPagePresenter;
import com.zihler.wiki.domain.entity.WikiPage;
import com.zihler.wiki.domain.values.Body;
import com.zihler.wiki.domain.values.ReferenceTag;

public class CreatedWikiPage {

    private WikiPage self;
    private StoreWikiPage storeWikiPage;
    private WikiPagePresenter presenter;

    private CreatedWikiPage(WikiPage self, StoreWikiPage storeWikiPage, WikiPagePresenter presenter) {
        this.self = self;
        this.storeWikiPage = storeWikiPage;
        this.presenter = presenter;
    }

    public static CreatedWikiPage from(WikiPageDocument intendedWikiPage, FindWikiPage findWikiPage, StoreWikiPage storeWikiPage, WikiPagePresenter presenter) {
        WikiPage wikiPage = findWikiPage
                .by(intendedWikiPage.referenceTag())
                .orElse(WikiPage.empty());

        wikiPage.setReferenceTag(intendedWikiPage.referenceTag());
        wikiPage.setTitle(intendedWikiPage.title());
        wikiPage.setBody(Body.from(intendedWikiPage.body().toString()));

        return new CreatedWikiPage(wikiPage, storeWikiPage, presenter);
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

    public ReferenceTag getReferenceTag() {
        return self.getReferenceTag();
    }
}
