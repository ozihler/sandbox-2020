package com.zihler.wiki.adapters.spring;

import com.zihler.wiki.application.outbound_ports.documents.WikiPageDocument;
import com.zihler.wiki.application.outbound_ports.gateways.FindWikiPage;
import com.zihler.wiki.application.outbound_ports.gateways.RetrieveAllWikiPages;
import com.zihler.wiki.application.outbound_ports.gateways.StoreWikiPage;
import com.zihler.wiki.application.outbound_ports.presenters.WikiPagePresenter;
import com.zihler.wiki.application.use_cases.create_wiki_page_from_title.CreateWikiPageFromTitleUseCase;
import com.zihler.wiki.application.use_cases.create_wiki_pages_from_body.CreateWikiPagesFromBodyUseCase;
import com.zihler.wiki.application.use_cases.find_wiki_pages.FindAllWikiPagesUseCase;
import com.zihler.wiki.application.use_cases.find_wiki_pages.outbound_port.WikiPagesSearchResultPresenter;
import com.zihler.wiki.application.use_cases.update_wiki_page.UpdateWikiPageUseCase;
import com.zihler.wiki.application.use_cases.update_wiki_page.inbound_port.UpdateWikiPage;
import com.zihler.wiki.domain.values.Title;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpringWikiPagesFacade {

    private final CreateWikiPagesFromBodyUseCase createWikiPages;
    private final CreateWikiPageFromTitleUseCase createWikiPage;
    private final FindAllWikiPagesUseCase findAllWikiPagesUseCase;
    private UpdateWikiPage updateWikiPage;

    @Autowired
    public SpringWikiPagesFacade(FindWikiPage findWikiPages,
                                 StoreWikiPage storeWikiPage,
                                 RetrieveAllWikiPages retrieveAllWikiPages) {

        findAllWikiPagesUseCase = new FindAllWikiPagesUseCase(retrieveAllWikiPages);
        updateWikiPage = new UpdateWikiPageUseCase(findWikiPages, storeWikiPage);
        createWikiPage = new CreateWikiPageFromTitleUseCase(findWikiPages, storeWikiPage);
        createWikiPages = new CreateWikiPagesFromBodyUseCase(findWikiPages, storeWikiPage);
    }

    public void createSingleWikiPageFrom(Title title, WikiPagePresenter output) {
        createWikiPage.callWith(title, output);
    }

    public void createMultipleWikiPagesFrom(WikiPageDocument wikiPage, WikiPagePresenter output) {
        createWikiPages.callWith(wikiPage.body(), output);
        updateWikiPage.callWith(wikiPage, output);
    }

    public void findAllWikiPages(WikiPagesSearchResultPresenter output) {
        findAllWikiPagesUseCase.andSendThemTo(output);
    }
}
