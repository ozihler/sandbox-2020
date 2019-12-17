package com.zihler.wiki.adapters.facades;

import com.zihler.wiki.application.outbound_ports.gateway.FindWikiPage;
import com.zihler.wiki.application.outbound_ports.gateway.RetrieveAllWikiPages;
import com.zihler.wiki.application.outbound_ports.gateway.StoreWikiPage;
import com.zihler.wiki.application.outbound_ports.presenter.WikiPagePresenter;
import com.zihler.wiki.application.use_cases.create_wiki_page_from_title.CreateWikiPageFromTitleUseCase;
import com.zihler.wiki.application.use_cases.create_wiki_pages_from_body.CreateWikiPagesFromBodyUseCase;
import com.zihler.wiki.application.use_cases.find_wiki_pages.FindAllWikiPagesUseCase;
import com.zihler.wiki.application.use_cases.find_wiki_pages.outbound_port.WikiPagesSearchResultPresenter;
import com.zihler.wiki.domain.values.Body;
import com.zihler.wiki.domain.values.Title;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpringWikiPagesFacade {

    private final CreateWikiPagesFromBodyUseCase createWikiPages;
    private final CreateWikiPageFromTitleUseCase createWikiPage;
    private final FindAllWikiPagesUseCase findAllWikiPagesUseCase;

    @Autowired
    public SpringWikiPagesFacade(FindWikiPage findWikiPages,
                                 StoreWikiPage storeWikiPage,
                                 RetrieveAllWikiPages retrieveAllWikiPages) {

        findAllWikiPagesUseCase = new FindAllWikiPagesUseCase(retrieveAllWikiPages);
        createWikiPage = new CreateWikiPageFromTitleUseCase(findWikiPages, storeWikiPage);
        createWikiPages = new CreateWikiPagesFromBodyUseCase(findWikiPages, storeWikiPage);
    }

    public void createSingleWikiPageFrom(Title title, WikiPagePresenter output) {
        createWikiPage.from(title, output);
    }

    public void createMultipleWikiPagesFrom(Body body, WikiPagePresenter output) {
        createWikiPages.from(body, output);
    }

    public void findAllWikiPages(WikiPagesSearchResultPresenter output) {
        findAllWikiPagesUseCase.andSendThemTo(output);
    }
}
