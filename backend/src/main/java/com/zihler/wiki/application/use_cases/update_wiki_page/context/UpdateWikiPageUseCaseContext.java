package com.zihler.wiki.application.use_cases.update_wiki_page.context;

import com.zihler.wiki.application.outbound_ports.documents.WikiPageDocument;
import com.zihler.wiki.application.outbound_ports.gateways.FindWikiPage;
import com.zihler.wiki.application.outbound_ports.gateways.StoreWikiPage;
import com.zihler.wiki.application.outbound_ports.presenters.WikiPagePresenter;
import com.zihler.wiki.application.use_cases.UseCaseContext;
import com.zihler.wiki.application.use_cases.exceptions.WikiPageNotFoundException;
import com.zihler.wiki.application.use_cases.update_wiki_page.roles.UpdatedWikiPage;
import com.zihler.wiki.domain.entity.WikiPage;
import com.zihler.wiki.domain.values.Body;
import com.zihler.wiki.domain.values.ReferenceTag;

import static java.lang.String.format;

public class UpdateWikiPageUseCaseContext implements UseCaseContext {

    private UpdatedWikiPage updatedWikiPage;

    private UpdateWikiPageUseCaseContext(UpdatedWikiPage updatedWikiPage) {
        this.updatedWikiPage = updatedWikiPage;
    }

    public static UpdateWikiPageUseCaseContext initialize(WikiPageDocument newWikiPageData, FindWikiPage findWikiPages, StoreWikiPage storeWikiPage, WikiPagePresenter output) {
        WikiPage wikiPage = findWikiPages.by(newWikiPageData.referenceTag())
                .orElseThrow(() -> wikiPageNotFoundException(newWikiPageData.referenceTag()));

        wikiPage.setReferenceTag(newWikiPageData.referenceTag());
        wikiPage.setTitle(newWikiPageData.title());
        wikiPage.setBody(Body.from(newWikiPageData.body().toString()));

        UpdatedWikiPage updatedWikiPage = new UpdatedWikiPage(wikiPage, storeWikiPage, output);

        return new UpdateWikiPageUseCaseContext(updatedWikiPage);
    }

    private static WikiPageNotFoundException wikiPageNotFoundException(ReferenceTag referenceTag) {
        return new WikiPageNotFoundException(format("Could not update wiki page with reference tag %s because it does not exist.", referenceTag));
    }

    @Override
    public void enactUseCase() {
        updatedWikiPage.update();
        updatedWikiPage.present();
    }
}
