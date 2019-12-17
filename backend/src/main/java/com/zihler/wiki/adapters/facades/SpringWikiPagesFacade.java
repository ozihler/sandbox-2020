package com.zihler.wiki.adapters.facades;

import com.zihler.wiki.application.outbound_ports.documents.WikiPageDocument;
import com.zihler.wiki.application.outbound_ports.gateway.FindWikiPage;
import com.zihler.wiki.application.outbound_ports.gateway.RetrieveAllWikiPages;
import com.zihler.wiki.application.outbound_ports.gateway.StoreWikiPage;
import com.zihler.wiki.application.outbound_ports.presenter.Presenter;
import com.zihler.wiki.application.use_cases.create_multiple_wiki_pages.CreateMultipleWikiPagesUseCase;
import com.zihler.wiki.application.use_cases.create_single_wiki_page.CreateSingleWikiPageUseCase;
import com.zihler.wiki.application.use_cases.find_wiki_pages.FindAllWikiPagesUseCase;
import com.zihler.wiki.domain.values.Body;
import com.zihler.wiki.domain.values.Title;
import com.zihler.wiki.domain.values.WikiPagesDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpringWikiPagesFacade {

    private final CreateMultipleWikiPagesUseCase createWikiPages;
    private final CreateSingleWikiPageUseCase createWikiPage;
    private final FindAllWikiPagesUseCase findAllWikiPagesUseCase;

    @Autowired
    public SpringWikiPagesFacade(FindWikiPage findWikiPages,
                                 StoreWikiPage storeWikiPage,
                                 RetrieveAllWikiPages retrieveAllWikiPages) {

        findAllWikiPagesUseCase = new FindAllWikiPagesUseCase(retrieveAllWikiPages);
        createWikiPage = new CreateSingleWikiPageUseCase(findWikiPages, storeWikiPage);
        createWikiPages = new CreateMultipleWikiPagesUseCase(findWikiPages, storeWikiPage);
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
