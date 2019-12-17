package com.zihler.wiki.application.habits.create_wiki_page;

import com.zihler.wiki.application.habits.create_wiki_page.context.CreateWikiPageHabitContext;
import com.zihler.wiki.application.habits.create_wiki_page.inbound_ports.CreateWikiPage;
import com.zihler.wiki.application.outbound_ports.documents.WikiPageDocument;
import com.zihler.wiki.application.outbound_ports.gateway.FindWikiPage;
import com.zihler.wiki.application.outbound_ports.gateway.StoreWikiPage;
import com.zihler.wiki.application.outbound_ports.presenter.WikiPagePresenter;

public class CreateWikiPageHabit implements CreateWikiPage {
    private final FindWikiPage findWikiPage;
    private final StoreWikiPage storeWikiPage;

    public CreateWikiPageHabit(FindWikiPage findWikiPage, StoreWikiPage storeWikiPage) {
        this.findWikiPage = findWikiPage;
        this.storeWikiPage = storeWikiPage;
    }

    @Override
    public void with(WikiPageDocument intendedWikiPage, WikiPagePresenter presenter) {
        CreateWikiPageHabitContext
                .initialize(intendedWikiPage, findWikiPage, storeWikiPage, presenter)
                .enactHabit();
    }
}
