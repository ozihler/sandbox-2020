package com.zihler.wiki.application.use_cases.create_wiki_pages_from_body.context;

import com.zihler.wiki.application.outbound_ports.documents.WikiPagesDocument;
import com.zihler.wiki.application.outbound_ports.gateways.FindWikiPage;
import com.zihler.wiki.application.outbound_ports.gateways.StoreWikiPage;
import com.zihler.wiki.application.outbound_ports.presenters.WikiPagePresenter;
import com.zihler.wiki.application.use_cases.UseCaseContext;
import com.zihler.wiki.application.use_cases.create_wiki_pages_from_body.roles.BodyReferenceTags;
import com.zihler.wiki.application.use_cases.create_wiki_pages_from_body.roles.CreatedWikiPages;
import com.zihler.wiki.domain.values.Body;

public class CreateWikiPagesFromBodyUseCaseContext implements UseCaseContext {
    private CreatedWikiPages createdWikiPages;

    private CreateWikiPagesFromBodyUseCaseContext(CreatedWikiPages createdWikiPages) {
        this.createdWikiPages = createdWikiPages;
    }


    public static CreateWikiPagesFromBodyUseCaseContext initialize(Body body, FindWikiPage findWikiPage, StoreWikiPage storeWikiPage, WikiPagePresenter presenter) {
        BodyReferenceTags bodyReferenceTags = BodyReferenceTags.from(body);
        WikiPagesDocument extractedWikiPages = bodyReferenceTags.toWikiPagesDocument();

        CreatedWikiPages createdWikiPages = CreatedWikiPages.from(extractedWikiPages, presenter, findWikiPage, storeWikiPage);

        return new CreateWikiPagesFromBodyUseCaseContext(createdWikiPages);
    }

    @Override
    public void enactUseCase() {
        createdWikiPages.store();
        createdWikiPages.present();
    }
}
