package com.zihler.wiki.application.habits.create_wiki_page.context;

import com.zihler.wiki.application.habits.HabitContext;
import com.zihler.wiki.application.habits.create_wiki_page.roles.CreatedWikiPage;
import com.zihler.wiki.application.outbound_ports.documents.WikiPageDocument;
import com.zihler.wiki.application.outbound_ports.gateway.FindWikiPage;
import com.zihler.wiki.application.outbound_ports.gateway.StoreWikiPage;
import com.zihler.wiki.application.outbound_ports.presenter.WikiPagePresenter;
import com.zihler.wiki.domain.entity.WikiPage;

public class CreateWikiPageHabitContext implements HabitContext {

    private CreatedWikiPage wikiPage;

    private CreateWikiPageHabitContext(CreatedWikiPage wikiPage) {
        this.wikiPage = wikiPage;
    }

    public static CreateWikiPageHabitContext initialize(WikiPageDocument intendedWikiPage, FindWikiPage findWikiPage, StoreWikiPage storeWikiPage, WikiPagePresenter presenter) {
        WikiPage wikiPage = findOrCreateWikiPage(intendedWikiPage, findWikiPage);

        CreatedWikiPage createdWikiPage = new CreatedWikiPage(wikiPage, storeWikiPage, presenter);

        return new CreateWikiPageHabitContext(createdWikiPage);
    }

    private static WikiPage findOrCreateWikiPage(WikiPageDocument intendedWikiPage, FindWikiPage findWikiPage) {
        return findWikiPage.by(intendedWikiPage.getReferenceTag())
                .orElse(WikiPage.from(intendedWikiPage.getReferenceTag(), intendedWikiPage.getTitle()));
    }

    @Override
    public void enactHabit() {
        wikiPage.store()
                .present();
    }

}
