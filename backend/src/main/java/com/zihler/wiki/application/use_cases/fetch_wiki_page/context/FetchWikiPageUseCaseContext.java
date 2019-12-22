package com.zihler.wiki.application.use_cases.fetch_wiki_page.context;

import com.zihler.wiki.application.outbound_ports.gateways.FindWikiPage;
import com.zihler.wiki.application.outbound_ports.presenters.WikiPagePresenter;
import com.zihler.wiki.application.use_cases.UseCaseContext;
import com.zihler.wiki.application.use_cases.fetch_wiki_page.roles.FoundWikiPage;
import com.zihler.wiki.domain.values.ReferenceTag;

public class FetchWikiPageUseCaseContext implements UseCaseContext {
    private FoundWikiPage foundWikiPage;

    private FetchWikiPageUseCaseContext(FoundWikiPage foundWikiPage) {
        this.foundWikiPage = foundWikiPage;
    }

    public static FetchWikiPageUseCaseContext initialize(ReferenceTag referenceTag, FindWikiPage findWikiPage, WikiPagePresenter output) {
        FoundWikiPage foundWikiPage = new FoundWikiPage(findWikiPage.findOrThrow(referenceTag), output);

        return new FetchWikiPageUseCaseContext(foundWikiPage);
    }

    @Override
    public void enactUseCase() {
        foundWikiPage.present();
    }
}
