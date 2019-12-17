package com.zihler.wiki.application.use_cases.create_multiple_wiki_pages.context;

import com.zihler.wiki.application.outbound_ports.gateway.FindWikiPage;
import com.zihler.wiki.application.outbound_ports.gateway.StoreWikiPage;
import com.zihler.wiki.application.outbound_ports.presenter.WikiPagePresenter;
import com.zihler.wiki.application.use_cases.UseCaseContext;
import com.zihler.wiki.application.use_cases.create_multiple_wiki_pages.roles.BodyReferenceTags;
import com.zihler.wiki.domain.values.Body;

public class CreateMultipleWikiPagesUseCaseContext implements UseCaseContext {
    private BodyReferenceTags bodyReferenceTags;

    private CreateMultipleWikiPagesUseCaseContext(BodyReferenceTags bodyReferenceTags) {
        this.bodyReferenceTags = bodyReferenceTags;
    }


    public static CreateMultipleWikiPagesUseCaseContext initialize(Body body, FindWikiPage findWikiPage, StoreWikiPage storeWikiPage, WikiPagePresenter presenter) {
        BodyReferenceTags bodyReferenceTags = BodyReferenceTags.from(body, findWikiPage, storeWikiPage, presenter);

        return new CreateMultipleWikiPagesUseCaseContext(bodyReferenceTags);
    }

    @Override
    public void enactUseCase() {
        bodyReferenceTags.createAndStoreWikiPages();
    }
}
