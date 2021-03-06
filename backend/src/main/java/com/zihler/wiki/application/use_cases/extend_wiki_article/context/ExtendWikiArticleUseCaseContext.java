package com.zihler.wiki.application.use_cases.extend_wiki_article.context;

import com.zihler.wiki.application.outbound_ports.documents.WikiPageDocument;
import com.zihler.wiki.application.outbound_ports.documents.WikiPagesDocument;
import com.zihler.wiki.application.outbound_ports.gateways.FindWikiPage;
import com.zihler.wiki.application.outbound_ports.gateways.StoreWikiPage;
import com.zihler.wiki.application.outbound_ports.presenters.WikiPagePresenter;
import com.zihler.wiki.application.use_cases.UseCaseContext;
import com.zihler.wiki.application.use_cases.extend_wiki_article.roles.CreatedWikiPages;
import com.zihler.wiki.application.use_cases.extend_wiki_article.roles.ExtendedWikiPage;
import com.zihler.wiki.application.use_cases.extend_wiki_article.roles.ReferenceTagsFoundInBody;
import com.zihler.wiki.domain.entity.WikiPage;
import com.zihler.wiki.domain.values.Body;

public class ExtendWikiArticleUseCaseContext implements UseCaseContext {
    private Body updatedBody;
    private ExtendedWikiPage extendedWikiPage;
    private CreatedWikiPages createdWikiPages;

    private ExtendWikiArticleUseCaseContext(Body extendedBody, ExtendedWikiPage extendedWikiPage, CreatedWikiPages createdWikiPages) {
        updatedBody = extendedBody;
        this.extendedWikiPage = extendedWikiPage;
        this.createdWikiPages = createdWikiPages;
    }


    public static ExtendWikiArticleUseCaseContext initialize(WikiPageDocument theUpdate, FindWikiPage findWikiPage, StoreWikiPage storeWikiPage, WikiPagePresenter presenter) {
        WikiPage self = findWikiPage.findOrThrow(theUpdate.referenceTag());

        ExtendedWikiPage extendedWikiPage = new ExtendedWikiPage(self, storeWikiPage, presenter);

        WikiPagesDocument wikiPagesToStore = ReferenceTagsFoundInBody.from(theUpdate.body()).toWikiPagesDocument();

        CreatedWikiPages createdWikiPages = CreatedWikiPages.from(wikiPagesToStore, findWikiPage, storeWikiPage);

        return new ExtendWikiArticleUseCaseContext(theUpdate.body(), extendedWikiPage, createdWikiPages);
    }

    @Override
    public void enactUseCase() {
        createdWikiPages.storeAll()
                .andReferenceIn(extendedWikiPage);

        extendedWikiPage.extendWith(updatedBody);

        extendedWikiPage.present();
    }
}
