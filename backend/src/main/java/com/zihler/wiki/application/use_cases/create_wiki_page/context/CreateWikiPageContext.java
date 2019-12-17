package com.zihler.wiki.application.use_cases.create_wiki_page.context;

import com.zihler.wiki.application.outbound_ports.documents.WikiPageDocument;
import com.zihler.wiki.application.outbound_ports.gateway.FindWikiPageByTitle;
import com.zihler.wiki.application.outbound_ports.gateway.StoreWikiPage;
import com.zihler.wiki.application.outbound_ports.presenter.Presenter;
import com.zihler.wiki.application.use_cases.Context;
import com.zihler.wiki.application.use_cases.create_wiki_page.roles.CamelCaseTitle;
import com.zihler.wiki.application.use_cases.create_wiki_page.roles.CreatedWikiPage;
import com.zihler.wiki.application.use_cases.create_wiki_page.roles.ReferenceTagFromTitle;
import com.zihler.wiki.domain.entity.WikiPage;
import com.zihler.wiki.domain.exceptions.IllegalTitleException;
import com.zihler.wiki.domain.values.ReferenceTag;
import com.zihler.wiki.domain.values.Title;

import static java.lang.String.format;

public class CreateWikiPageContext implements Context {

    private CreatedWikiPage wikiPage;

    private CreateWikiPageContext(CreatedWikiPage wikiPage) {
        this.wikiPage = wikiPage;
    }

    public static CreateWikiPageContext initialize(Title title, FindWikiPageByTitle findWikiPage, StoreWikiPage storeWikiPage, Presenter<WikiPageDocument> presenter) {
        if (findWikiPage.with(title)) {
            throw new IllegalTitleException(format("Title named %s exists already", title));
        }

        CamelCaseTitle camelCaseTitle = new CamelCaseTitle(title);
        ReferenceTagFromTitle referenceTagFromTitle = new ReferenceTagFromTitle(camelCaseTitle);
        ReferenceTag referenceTag = referenceTagFromTitle.get();
        WikiPage wikiPageToCreate = WikiPage.from(referenceTag, title);

        CreatedWikiPage wikiPage = new CreatedWikiPage(wikiPageToCreate, storeWikiPage, presenter);

        return new CreateWikiPageContext(wikiPage);
    }

    @Override
    public void enactUseCase() {
        wikiPage.store()
                .present();
    }
}
