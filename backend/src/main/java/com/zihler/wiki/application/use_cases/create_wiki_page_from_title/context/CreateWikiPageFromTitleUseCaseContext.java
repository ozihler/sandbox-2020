package com.zihler.wiki.application.use_cases.create_wiki_page_from_title.context;

import com.zihler.wiki.application.outbound_ports.documents.WikiPageDocument;
import com.zihler.wiki.application.outbound_ports.gateways.FindWikiPage;
import com.zihler.wiki.application.outbound_ports.gateways.StoreWikiPage;
import com.zihler.wiki.application.outbound_ports.presenters.WikiPagePresenter;
import com.zihler.wiki.application.use_cases.UseCaseContext;
import com.zihler.wiki.application.use_cases.create_wiki_page_from_title.roles.CreatedWikiPage;
import com.zihler.wiki.application.use_cases.create_wiki_page_from_title.roles.TitleBasedReferenceTag;
import com.zihler.wiki.domain.values.Body;
import com.zihler.wiki.domain.values.ReferenceTag;
import com.zihler.wiki.domain.values.Title;

public class CreateWikiPageFromTitleUseCaseContext implements UseCaseContext {
    private CreatedWikiPage createdWikiPage;

    private CreateWikiPageFromTitleUseCaseContext(CreatedWikiPage createdWikiPage) {
        this.createdWikiPage = createdWikiPage;
    }

    public static CreateWikiPageFromTitleUseCaseContext initialize(Title title, FindWikiPage findWikiPage, StoreWikiPage storeWikiPage, WikiPagePresenter presenter) {
        ReferenceTag referenceTag = TitleBasedReferenceTag.from(title).get();
        WikiPageDocument intendedWikiPage = WikiPageDocument.from(referenceTag, title, Body.empty());

        CreatedWikiPage createdWikiPage = CreatedWikiPage.from(intendedWikiPage, findWikiPage, storeWikiPage, presenter);

        return new CreateWikiPageFromTitleUseCaseContext(createdWikiPage);
    }

    @Override
    public void enactUseCase() {
        createdWikiPage.store();
        createdWikiPage.present();
    }
}
