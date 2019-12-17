package com.zihler.wiki.adapters.facades;

import com.zihler.wiki.application.outbound_ports.documents.WikiPageDocument;
import com.zihler.wiki.application.outbound_ports.gateway.FindWikiPageByReferenceTag;
import com.zihler.wiki.application.outbound_ports.gateway.FindWikiPageByTitle;
import com.zihler.wiki.application.outbound_ports.gateway.RetrieveAllWikiPages;
import com.zihler.wiki.application.outbound_ports.gateway.StoreWikiPage;
import com.zihler.wiki.application.outbound_ports.presenter.Presenter;
import com.zihler.wiki.application.use_cases.create_wiki_page.CreateWikiPageUseCase;
import com.zihler.wiki.application.use_cases.create_wiki_pages.CreateWikiPagesUseCase;
import com.zihler.wiki.application.use_cases.find_wiki_pages.FindAllWikiPagesUseCase;
import com.zihler.wiki.domain.values.Body;
import com.zihler.wiki.domain.values.Title;
import com.zihler.wiki.domain.values.WikiPagesDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpringWikiPagesFacade {

    private final CreateWikiPagesUseCase createWikiPages;
    private final CreateWikiPageUseCase createWikiPage;
    private final FindAllWikiPagesUseCase findAllWikiPagesUseCase;

    @Autowired
    public SpringWikiPagesFacade(FindWikiPageByReferenceTag findWikiPages,
                                 StoreWikiPage storeWikiPage,
                                 FindWikiPageByTitle findWikiPageByTitle,
                                 RetrieveAllWikiPages retrieveAllWikiPages) {

        findAllWikiPagesUseCase = new FindAllWikiPagesUseCase(retrieveAllWikiPages);
        createWikiPage = new CreateWikiPageUseCase(findWikiPageByTitle, storeWikiPage);
        createWikiPages = new CreateWikiPagesUseCase(findWikiPages, storeWikiPage);
    }

    public void createSingleWikiPageFrom(Title title, Presenter<WikiPageDocument> presenter) {
        createWikiPage.from(title, presenter);
    }

    public void createMultipleWikiPagesFrom(Body body, Presenter<WikiPagesDocument> presenter) {
        createWikiPages.from(body, presenter);
    }

    public void findAllWikiPages(Presenter<WikiPagesDocument> presenter) {
        findAllWikiPagesUseCase.callWith(presenter);
    }
}
