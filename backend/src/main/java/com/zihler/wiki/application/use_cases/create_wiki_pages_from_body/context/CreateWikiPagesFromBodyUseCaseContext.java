package com.zihler.wiki.application.use_cases.create_wiki_pages_from_body.context;

import com.zihler.wiki.application.habits.create_wiki_page.CreateWikiPageHabit;
import com.zihler.wiki.application.outbound_ports.gateway.FindWikiPage;
import com.zihler.wiki.application.outbound_ports.gateway.StoreWikiPage;
import com.zihler.wiki.application.outbound_ports.presenter.WikiPagePresenter;
import com.zihler.wiki.application.use_cases.UseCaseContext;
import com.zihler.wiki.application.use_cases.create_wiki_pages_from_body.roles.BodyReferenceTags;
import com.zihler.wiki.application.use_cases.create_wiki_pages_from_body.roles.CreatedWikiPagesFromBody;
import com.zihler.wiki.domain.values.Body;
import com.zihler.wiki.domain.values.WikiPagesDocument;

public class CreateWikiPagesFromBodyUseCaseContext implements UseCaseContext {
    private CreatedWikiPagesFromBody createdWikiPagesFromBody;

    private CreateWikiPagesFromBodyUseCaseContext(CreatedWikiPagesFromBody createdWikiPagesFromBody) {
        this.createdWikiPagesFromBody = createdWikiPagesFromBody;
    }


    public static CreateWikiPagesFromBodyUseCaseContext initialize(Body body, FindWikiPage findWikiPage, StoreWikiPage storeWikiPage, WikiPagePresenter presenter) {
        BodyReferenceTags bodyReferenceTags = BodyReferenceTags.from(body);
        WikiPagesDocument extractedWikiPages = bodyReferenceTags.toWikiPagesDocument();

        CreateWikiPageHabit createWikiPage = new CreateWikiPageHabit(findWikiPage, storeWikiPage);

        CreatedWikiPagesFromBody createdWikiPagesFromBody = CreatedWikiPagesFromBody.from(extractedWikiPages, presenter, createWikiPage);

        return new CreateWikiPagesFromBodyUseCaseContext(createdWikiPagesFromBody);
    }

    @Override
    public void enactUseCase() {
        createdWikiPagesFromBody.storeAndPresent();
    }
}
