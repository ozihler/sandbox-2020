package com.zihler.wiki.application.use_cases.create_wiki_pages;

import com.zihler.wiki.application.use_cases.outbound_ports.documents.WikiPageDocument;
import com.zihler.wiki.application.use_cases.outbound_ports.gateway.FindWikiPage;
import com.zihler.wiki.application.use_cases.outbound_ports.gateway.StoreWikiPage;
import com.zihler.wiki.domain.entity.WikiPage;
import com.zihler.wiki.domain.exceptions.IllegalTitleException;
import com.zihler.wiki.domain.values.ReferenceTag;
import com.zihler.wiki.domain.values.Title;

import static java.lang.String.format;

class CreatedWikiPage {

    private WikiPage self;
    private StoreWikiPage storeWikiPage;

    private CreatedWikiPage(WikiPage wikiPage, StoreWikiPage storeWikiPage) {
        self = wikiPage;
        this.storeWikiPage = storeWikiPage;
    }

    static CreatedWikiPage create(Title title, FindWikiPage<Title> findWikiPage, StoreWikiPage storeWikiPage) {
        if (findWikiPage.having(title)) {
            throw new IllegalTitleException(format("Title named %s exists already", title));
        }

        ReferenceTag referenceTag = ReferenceTag.from(title);
        WikiPage wikiPage = WikiPage.from(referenceTag, title);

        return new CreatedWikiPage(wikiPage, storeWikiPage);
    }

    void store() {
        this.self = storeWikiPage.as(self);
    }

    WikiPageDocument asDocument() {
        return WikiPageDocument.of(self);
    }

}
