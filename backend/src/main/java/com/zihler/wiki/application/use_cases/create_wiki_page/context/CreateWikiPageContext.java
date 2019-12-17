package com.zihler.wiki.application.use_cases.create_wiki_page.context;

import com.zihler.wiki.application.outbound_ports.documents.WikiPageDocument;
import com.zihler.wiki.application.outbound_ports.gateway.FindWikiPage;
import com.zihler.wiki.application.outbound_ports.gateway.StoreWikiPage;
import com.zihler.wiki.application.outbound_ports.presenter.Presenter;
import com.zihler.wiki.application.use_cases.Context;
import com.zihler.wiki.application.use_cases.create_wiki_page.roles.CamelCaseTitle;
import com.zihler.wiki.application.use_cases.create_wiki_page.roles.CreatedWikiPage;
import com.zihler.wiki.application.use_cases.create_wiki_page.roles.TitleReferenceTag;
import com.zihler.wiki.domain.values.Title;

public class CreateWikiPageContext implements Context {

    private CreatedWikiPage wikiPage;

    private CreateWikiPageContext(CreatedWikiPage wikiPage) {
        this.wikiPage = wikiPage;
    }

    public static CreateWikiPageContext initialize(Title title, FindWikiPage findWikiPage, StoreWikiPage storeWikiPage, Presenter<WikiPageDocument> presenter) {

        var camelCaseTitle = CamelCaseTitle.from(title);
        var titleReferenceTag = TitleReferenceTag.from(camelCaseTitle);

        var wikiPage = CreatedWikiPage.from(title, titleReferenceTag.get(), storeWikiPage, findWikiPage, presenter);

        return new CreateWikiPageContext(wikiPage);
    }

    @Override
    public void enactUseCase() {
        wikiPage.store()
                .present();
    }
}
