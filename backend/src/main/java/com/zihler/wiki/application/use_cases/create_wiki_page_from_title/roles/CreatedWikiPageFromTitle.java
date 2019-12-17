package com.zihler.wiki.application.use_cases.create_wiki_page_from_title.roles;

import com.zihler.wiki.application.habits.create_wiki_page.inbound_ports.CreateWikiPage;
import com.zihler.wiki.application.outbound_ports.documents.WikiPageDocument;
import com.zihler.wiki.application.outbound_ports.presenter.WikiPagePresenter;
import com.zihler.wiki.domain.entity.WikiPage;

public class CreatedWikiPageFromTitle {
    private final WikiPage self;
    private final WikiPagePresenter presenter;
    private final CreateWikiPage createWikiPage;

    private CreatedWikiPageFromTitle(WikiPage self, CreateWikiPage createWikiPage, WikiPagePresenter presenter) {
        this.self = self;
        this.presenter = presenter;
        this.createWikiPage = createWikiPage;
    }

    public static CreatedWikiPageFromTitle from(WikiPageDocument intendedWikiPage, CreateWikiPage createWikiPage, WikiPagePresenter presenter) {
        WikiPage wikiPage = WikiPage.from(intendedWikiPage.getReferenceTag(), intendedWikiPage.getTitle());

        return new CreatedWikiPageFromTitle(wikiPage, createWikiPage, presenter);
    }

    public void storeAndPresent() {
        createWikiPage.with(WikiPageDocument.of(self), presenter);
    }
}
