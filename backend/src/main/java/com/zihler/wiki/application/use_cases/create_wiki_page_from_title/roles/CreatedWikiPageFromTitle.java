package com.zihler.wiki.application.use_cases.create_wiki_page_from_title.roles;

import com.zihler.wiki.application.habits.create_wiki_page.context.CreateWikiPageHabitContext;
import com.zihler.wiki.application.outbound_ports.documents.WikiPageDocument;
import com.zihler.wiki.application.outbound_ports.gateway.FindWikiPage;
import com.zihler.wiki.application.outbound_ports.gateway.StoreWikiPage;
import com.zihler.wiki.application.outbound_ports.presenter.WikiPagePresenter;

public class CreatedWikiPageFromTitle {
    private final WikiPageDocument intendedWikiPage;
    private final FindWikiPage findWikiPage;
    private final StoreWikiPage storeWikiPage;
    private final WikiPagePresenter presenter;

    private CreatedWikiPageFromTitle(WikiPageDocument intendedWikiPage, FindWikiPage findWikiPage, StoreWikiPage storeWikiPage, WikiPagePresenter presenter) {

        this.intendedWikiPage = intendedWikiPage;
        this.findWikiPage = findWikiPage;
        this.storeWikiPage = storeWikiPage;
        this.presenter = presenter;
    }

    public static CreatedWikiPageFromTitle create(WikiPageDocument intendedWikiPage, FindWikiPage findWikiPage, StoreWikiPage storeWikiPage, WikiPagePresenter presenter) {
        return new CreatedWikiPageFromTitle(intendedWikiPage, findWikiPage, storeWikiPage, presenter);
    }

    public void storeAndPresent() {
        CreateWikiPageHabitContext
                .initialize(intendedWikiPage, findWikiPage, storeWikiPage, presenter)
                .enactHabit();
    }
}
