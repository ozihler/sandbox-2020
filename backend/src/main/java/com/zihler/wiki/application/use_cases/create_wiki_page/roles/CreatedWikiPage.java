package com.zihler.wiki.application.use_cases.create_wiki_page.roles;

import com.zihler.wiki.application.outbound_ports.documents.WikiPageDocument;
import com.zihler.wiki.application.outbound_ports.gateway.FindWikiPageByTitle;
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

    public static CreatedWikiPage create(Title title, FindWikiPageByTitle findWikiPage, StoreWikiPage storeWikiPage, Presenter<WikiPageDocument> presenter) {
        if (findWikiPage.existsWith(title)) {
            throw new IllegalTitleException(format("Title named %s exists already", title));
        }

        ReferenceTag referenceTag = ReferenceTag.from(title);
        WikiPage wikiPage = WikiPage.from(referenceTag, title);

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
}
