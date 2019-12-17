package com.zihler.wiki.application.use_cases.create_single_wiki_page.context;

import com.zihler.wiki.application.outbound_ports.gateway.FindWikiPage;
import com.zihler.wiki.application.outbound_ports.gateway.StoreWikiPage;
import com.zihler.wiki.application.outbound_ports.presenter.WikiPagePresenter;
import com.zihler.wiki.application.use_cases.UseCaseContext;
import com.zihler.wiki.application.use_cases.create_single_wiki_page.roles.CamelCaseTitle;
import com.zihler.wiki.application.use_cases.create_single_wiki_page.roles.CreatedWikiPage;
import com.zihler.wiki.application.use_cases.create_single_wiki_page.roles.ReferenceTagFromCamelCaseTitle;
import com.zihler.wiki.domain.values.Title;

public class CreateSingleWikiPageUseCaseContext implements UseCaseContext {

    private CreatedWikiPage wikiPage;

    private CreateSingleWikiPageUseCaseContext(CreatedWikiPage wikiPage) {
        this.wikiPage = wikiPage;
    }

    public static CreateSingleWikiPageUseCaseContext initialize(Title title, FindWikiPage findWikiPage, StoreWikiPage storeWikiPage, WikiPagePresenter presenter) {
        var camelCaseTitle = CamelCaseTitle.from(title);
        var titleReferenceTag = ReferenceTagFromCamelCaseTitle.from(camelCaseTitle);

        var wikiPage = CreatedWikiPage.from(title, titleReferenceTag.get(), storeWikiPage, findWikiPage, presenter);

        return new CreateSingleWikiPageUseCaseContext(wikiPage);
    }

    @Override
    public void enactUseCase() {
        wikiPage.store()
                .present();
    }
}
