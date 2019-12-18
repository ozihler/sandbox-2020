package com.zihler.wiki.adapters.spring;

import com.zihler.wiki.application.outbound_ports.documents.WikiPageDocument;
import com.zihler.wiki.application.outbound_ports.gateways.FindWikiPage;
import com.zihler.wiki.application.outbound_ports.gateways.RetrieveAllWikiPages;
import com.zihler.wiki.application.outbound_ports.gateways.StoreWikiPage;
import com.zihler.wiki.application.outbound_ports.presenters.WikiPagePresenter;
import com.zihler.wiki.application.use_cases.create_wiki_page_from_title.CreateWikiPageFromTitleUseCase;
import com.zihler.wiki.application.use_cases.create_wiki_page_from_title.inbound_port.CreateWikiPageFromTitle;
import com.zihler.wiki.application.use_cases.create_wiki_pages_from_body.ExtendWikiArticleUseCase;
import com.zihler.wiki.application.use_cases.create_wiki_pages_from_body.inbound_port.ExtendWikiArticle;
import com.zihler.wiki.application.use_cases.find_wiki_pages.FindAllWikiPagesUseCase;
import com.zihler.wiki.application.use_cases.find_wiki_pages.inbound_port.FindAllWikiPages;
import com.zihler.wiki.application.use_cases.find_wiki_pages.outbound_port.WikiPagesSearchResultPresenter;
import com.zihler.wiki.domain.values.Title;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpringWikiPagesFacade {

    private final ExtendWikiArticle extendWikiArticle;
    private final CreateWikiPageFromTitle createWikiPage;
    private final FindAllWikiPages findAllWikiPagesUseCase;

    @Autowired
    public SpringWikiPagesFacade(FindWikiPage findWikiPages,
                                 StoreWikiPage storeWikiPage,
                                 RetrieveAllWikiPages retrieveAllWikiPages) {

        findAllWikiPagesUseCase = new FindAllWikiPagesUseCase(retrieveAllWikiPages);
        createWikiPage = new CreateWikiPageFromTitleUseCase(findWikiPages, storeWikiPage);
        extendWikiArticle = new ExtendWikiArticleUseCase(findWikiPages, storeWikiPage);
    }

    public void createSingleWikiPageFrom(Title title, WikiPagePresenter output) {
        createWikiPage.callWith(title, output);
    }

    public void extendWikiArticle(WikiPageDocument wikiPage, WikiPagePresenter output) {
        extendWikiArticle.with(wikiPage, output);
    }

    public void findAllWikiPages(WikiPagesSearchResultPresenter output) {
        findAllWikiPagesUseCase.andSendThemTo(output);
    }
}
