package com.zihler.wiki.application.use_cases.extend_wiki_article;

import com.zihler.wiki.application.outbound_ports.documents.WikiPageDocument;
import com.zihler.wiki.application.outbound_ports.gateways.FindWikiPage;
import com.zihler.wiki.application.outbound_ports.gateways.StoreWikiPage;
import com.zihler.wiki.application.outbound_ports.presenters.WikiPagePresenter;
import com.zihler.wiki.application.use_cases.extend_wiki_article.context.ExtendWikiArticleUseCaseContext;
import com.zihler.wiki.application.use_cases.extend_wiki_article.inbound_port.ExtendWikiArticle;

public class ExtendWikiArticleUseCase implements ExtendWikiArticle {

    private final FindWikiPage findWikiPage;
    private final StoreWikiPage storeWikiPage;

    public ExtendWikiArticleUseCase(FindWikiPage findWikiPage, StoreWikiPage storeWikiPage) {
        this.findWikiPage = findWikiPage;
        this.storeWikiPage = storeWikiPage;
    }

    @Override
    public void with(WikiPageDocument extendedWikiPage, WikiPagePresenter presenter) {
        ExtendWikiArticleUseCaseContext
                .initialize(extendedWikiPage, findWikiPage, storeWikiPage, presenter)
                .enactUseCase();
    }
}
