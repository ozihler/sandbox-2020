package com.zihler.wiki.application.use_cases.create_wiki_pages_from_body.roles;

import com.zihler.wiki.application.habits.create_wiki_page.context.CreateWikiPageHabitContext;
import com.zihler.wiki.application.outbound_ports.documents.WikiPageDocument;
import com.zihler.wiki.application.outbound_ports.gateway.FindWikiPage;
import com.zihler.wiki.application.outbound_ports.gateway.StoreWikiPage;
import com.zihler.wiki.application.outbound_ports.presenter.WikiPagePresenter;
import com.zihler.wiki.domain.values.WikiPagesDocument;

public class CreatedWikiPagesFromBody {
    private final FindWikiPage findWikiPage;
    private final StoreWikiPage storeWikiPage;
    private final WikiPagePresenter presenter;
    private final WikiPagesDocument self;

    private CreatedWikiPagesFromBody(WikiPagesDocument self, FindWikiPage findWikiPage, StoreWikiPage storeWikiPage, WikiPagePresenter presenter) {
        this.self = self;
        this.findWikiPage = findWikiPage;
        this.storeWikiPage = storeWikiPage;
        this.presenter = presenter;
    }

    public static CreatedWikiPagesFromBody create(WikiPagesDocument intendedWikiPages, FindWikiPage findWikiPage, StoreWikiPage storeWikiPage, WikiPagePresenter presenter) {
        return new CreatedWikiPagesFromBody(intendedWikiPages, findWikiPage, storeWikiPage, presenter);
    }

    public void storeAndPresent() {
        for (WikiPageDocument intendedWikiPage : self.getWikiPages()) {
            CreateWikiPageHabitContext
                    .initialize(intendedWikiPage, findWikiPage, storeWikiPage, presenter)
                    .enactHabit();
        }
    }
}
