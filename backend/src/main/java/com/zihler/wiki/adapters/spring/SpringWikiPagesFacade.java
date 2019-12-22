package com.zihler.wiki.adapters.spring;

import com.zihler.wiki.application.outbound_ports.documents.WikiPageDocument;
import com.zihler.wiki.application.outbound_ports.gateways.FindWikiPage;
import com.zihler.wiki.application.outbound_ports.gateways.RetrieveAllWikiPages;
import com.zihler.wiki.application.outbound_ports.gateways.StoreWikiPage;
import com.zihler.wiki.application.outbound_ports.presenters.WikiPagePresenter;
import com.zihler.wiki.application.use_cases.create_wiki_page_from_title.CreateWikiPageFromTitleUseCase;
import com.zihler.wiki.application.use_cases.create_wiki_page_from_title.inbound_port.CreateWikiPageFromTitle;
import com.zihler.wiki.application.use_cases.extend_wiki_article.ExtendWikiArticleUseCase;
import com.zihler.wiki.application.use_cases.extend_wiki_article.inbound_port.ExtendWikiArticle;
import com.zihler.wiki.application.use_cases.fetch_wiki_page.FetchWikiPageUseCase;
import com.zihler.wiki.application.use_cases.fetch_wiki_page.inbound_port.FetchWikiPage;
import com.zihler.wiki.application.use_cases.find_wiki_pages.FindAllWikiPagesUseCase;
import com.zihler.wiki.application.use_cases.find_wiki_pages.inbound_port.FindAllWikiPages;
import com.zihler.wiki.application.use_cases.find_wiki_pages.outbound_port.WikiPagesSearchResultPresenter;
import com.zihler.wiki.domain.values.ReferenceTag;
import com.zihler.wiki.domain.values.Title;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpringWikiPagesFacade {

    private final ExtendWikiArticle extendWikiArticle;
    private final CreateWikiPageFromTitle createWikiPage;
    private final FindAllWikiPages findAllWikiPages;
    private final FetchWikiPage fetchWikiPage;

    @Autowired
    public SpringWikiPagesFacade(FindWikiPage findWikiPage,
                                 StoreWikiPage storeWikiPage,
                                 RetrieveAllWikiPages retrieveAllWikiPages) {

        findAllWikiPages = new FindAllWikiPagesUseCase(retrieveAllWikiPages);
        createWikiPage = new CreateWikiPageFromTitleUseCase(findWikiPage, storeWikiPage);
        extendWikiArticle = new ExtendWikiArticleUseCase(findWikiPage, storeWikiPage);
        fetchWikiPage = new FetchWikiPageUseCase(findWikiPage);
    }

    public void createSingleWikiPageFrom(Title title, WikiPagePresenter output) {
        createWikiPage.callWith(title, output);
    }

    public void extendWikiArticle(WikiPageDocument wikiPage, WikiPagePresenter output) {
        extendWikiArticle.with(wikiPage, output);
    }

    public void findAllWikiPages(WikiPagesSearchResultPresenter output) {
        findAllWikiPages.andSendThemTo(output);
    }

    public void fetchWikiPageByReferenceTag(ReferenceTag referenceTag, WikiPagePresenter output) {
        fetchWikiPage.byReferenceTag(referenceTag, output);
    }
}
