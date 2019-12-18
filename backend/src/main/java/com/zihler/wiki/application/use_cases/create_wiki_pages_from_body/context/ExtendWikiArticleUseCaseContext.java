package com.zihler.wiki.application.use_cases.create_wiki_pages_from_body.context;

import com.zihler.wiki.application.outbound_ports.documents.WikiPageDocument;
import com.zihler.wiki.application.outbound_ports.documents.WikiPagesDocument;
import com.zihler.wiki.application.outbound_ports.gateways.FindWikiPage;
import com.zihler.wiki.application.outbound_ports.gateways.StoreWikiPage;
import com.zihler.wiki.application.outbound_ports.presenters.WikiPagePresenter;
import com.zihler.wiki.application.use_cases.UseCaseContext;
import com.zihler.wiki.application.use_cases.create_wiki_pages_from_body.roles.BodyReferenceTags;
import com.zihler.wiki.application.use_cases.create_wiki_pages_from_body.roles.CreatedWikiPages;
import com.zihler.wiki.application.use_cases.create_wiki_pages_from_body.roles.ExistingWikiPage;
import com.zihler.wiki.domain.values.Body;

public class ExtendWikiArticleUseCaseContext implements UseCaseContext {
    private Body updatedBody;
    private ExistingWikiPage existingWikiPage;
    private CreatedWikiPages createdWikiPages;

    private ExtendWikiArticleUseCaseContext(Body updatedBody, ExistingWikiPage existingWikiPage, CreatedWikiPages createdWikiPages) {
        this.updatedBody = updatedBody;
        this.existingWikiPage = existingWikiPage;
        this.createdWikiPages = createdWikiPages;
    }


    public static ExtendWikiArticleUseCaseContext initialize(WikiPageDocument updatedWikiPage, FindWikiPage findWikiPage, StoreWikiPage storeWikiPage, WikiPagePresenter presenter) {
        ExistingWikiPage existingWikiPage = ExistingWikiPage.from(updatedWikiPage.referenceTag(), findWikiPage, presenter);

        Body updatedBody = Body.from(updatedWikiPage.body().toString());

        BodyReferenceTags bodyReferenceTags = BodyReferenceTags.from(updatedBody);

        WikiPagesDocument wikiPagesToStore = bodyReferenceTags.toWikiPagesDocument();

        CreatedWikiPages createdWikiPages = CreatedWikiPages.from(wikiPagesToStore, findWikiPage, storeWikiPage);

        return new ExtendWikiArticleUseCaseContext(updatedBody, existingWikiPage, createdWikiPages);
    }

    @Override
    public void enactUseCase() {
        existingWikiPage.updateWith(updatedBody);

        createdWikiPages.storeAll()
                .linkWith(existingWikiPage);

        existingWikiPage.present();
    }
}
