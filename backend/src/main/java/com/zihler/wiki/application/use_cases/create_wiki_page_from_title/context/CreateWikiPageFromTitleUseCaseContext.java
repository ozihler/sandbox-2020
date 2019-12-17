package com.zihler.wiki.application.use_cases.create_wiki_page_from_title.context;

import com.zihler.wiki.application.outbound_ports.documents.WikiPageDocument;
import com.zihler.wiki.application.outbound_ports.gateway.FindWikiPage;
import com.zihler.wiki.application.outbound_ports.gateway.StoreWikiPage;
import com.zihler.wiki.application.outbound_ports.presenter.WikiPagePresenter;
import com.zihler.wiki.application.use_cases.UseCaseContext;
import com.zihler.wiki.application.use_cases.create_wiki_page_from_title.roles.CreatedWikiPageFromTitle;
import com.zihler.wiki.application.use_cases.create_wiki_page_from_title.roles.TitleBasedReferenceTag;
import com.zihler.wiki.domain.values.ReferenceTag;
import com.zihler.wiki.domain.values.Title;

public class CreateWikiPageFromTitleUseCaseContext implements UseCaseContext {
    private CreatedWikiPageFromTitle createdWikiPageFromTitle;

    private CreateWikiPageFromTitleUseCaseContext(CreatedWikiPageFromTitle createdWikiPageFromTitle) {
        this.createdWikiPageFromTitle = createdWikiPageFromTitle;
    }

    public static CreateWikiPageFromTitleUseCaseContext initialize(Title title, FindWikiPage findWikiPage, StoreWikiPage storeWikiPage, WikiPagePresenter presenter) {
        ReferenceTag referenceTag = TitleBasedReferenceTag.from(title).get();
        WikiPageDocument intendedWikiPage = WikiPageDocument.from(title, referenceTag);

        CreatedWikiPageFromTitle createdWikiPageFromTitle = CreatedWikiPageFromTitle.create(intendedWikiPage, findWikiPage, storeWikiPage, presenter);

        return new CreateWikiPageFromTitleUseCaseContext(createdWikiPageFromTitle);
    }

    @Override
    public void enactUseCase() {
        createdWikiPageFromTitle.storeAndPresent();
    }
}
