package com.zihler.wiki.application.use_cases.create_wiki_page_from_title.context;

import com.zihler.wiki.application.habits.create_wiki_page.CreateWikiPageHabit;
import com.zihler.wiki.application.habits.create_wiki_page.inbound_ports.CreateWikiPage;
import com.zihler.wiki.application.outbound_ports.documents.WikiPageDocument;
import com.zihler.wiki.application.outbound_ports.gateway.FindWikiPage;
import com.zihler.wiki.application.outbound_ports.gateway.StoreWikiPage;
import com.zihler.wiki.application.outbound_ports.presenter.WikiPagePresenter;
import com.zihler.wiki.application.use_cases.UseCaseContext;
import com.zihler.wiki.application.use_cases.create_wiki_page_from_title.roles.TitleBasedReferenceTag;
import com.zihler.wiki.domain.values.Title;

public class CreateWikiPageFromTitleUseCaseContext implements UseCaseContext {
    private final WikiPageDocument intendedWikiPage;
    private WikiPagePresenter presenter;
    private CreateWikiPage createWikiPage;

    private CreateWikiPageFromTitleUseCaseContext(WikiPageDocument intendedWikiPage, WikiPagePresenter presenter, CreateWikiPage createWikiPage) {
        this.presenter = presenter;
        this.createWikiPage = createWikiPage;
        this.intendedWikiPage = intendedWikiPage;
    }

    public static CreateWikiPageFromTitleUseCaseContext initialize(Title title, WikiPagePresenter presenter, FindWikiPage findWikiPage, StoreWikiPage storeWikiPage) {
        var referenceTag = TitleBasedReferenceTag.from(title).get();
        var intendedWikiPage = WikiPageDocument.from(title, referenceTag);
        var createWikiPage = new CreateWikiPageHabit(findWikiPage, storeWikiPage);

        return new CreateWikiPageFromTitleUseCaseContext(intendedWikiPage, presenter, createWikiPage);
    }

    @Override
    public void enactUseCase() {
        createWikiPage.with(intendedWikiPage, presenter);
    }
}
