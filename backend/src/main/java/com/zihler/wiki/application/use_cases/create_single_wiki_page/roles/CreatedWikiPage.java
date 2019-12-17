package com.zihler.wiki.application.use_cases.create_single_wiki_page.roles;

import com.zihler.wiki.application.outbound_ports.documents.WikiPageDocument;
import com.zihler.wiki.application.outbound_ports.gateway.FindWikiPage;
import com.zihler.wiki.application.outbound_ports.gateway.StoreWikiPage;
import com.zihler.wiki.application.outbound_ports.presenter.Presenter;
import com.zihler.wiki.domain.entity.WikiPage;
import com.zihler.wiki.domain.exceptions.IllegalTitleException;
import com.zihler.wiki.domain.values.ReferenceTag;
import com.zihler.wiki.domain.values.Title;

import static java.lang.String.format;

public class CreatedWikiPage {

    private WikiPage self;
    private StoreWikiPage storeWikiPage;
    private Presenter<WikiPageDocument> presenter;

    private CreatedWikiPage(WikiPage wikiPage, StoreWikiPage storeWikiPage, Presenter<WikiPageDocument> presenter) {
        self = wikiPage;
        this.storeWikiPage = storeWikiPage;
        this.presenter = presenter;
    }

    public static CreatedWikiPage from(Title title,
                                       ReferenceTag referenceTag,
                                       StoreWikiPage storeWikiPage,
                                       FindWikiPage findWikiPage,
                                       Presenter<WikiPageDocument> presenter) {

        if (findWikiPage.with(referenceTag)) {
            throw new IllegalTitleException(format("Wiki Page with reference tag %s exists already", title));
        }

        WikiPage wikiPage = WikiPage.from(referenceTag, title);

        return new CreatedWikiPage(wikiPage, storeWikiPage, presenter);
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
