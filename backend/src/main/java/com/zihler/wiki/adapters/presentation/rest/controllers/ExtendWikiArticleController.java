package com.zihler.wiki.adapters.presentation.rest.controllers;

import com.zihler.wiki.adapters.presentation.rest.dtos.WikiPageDto;
import com.zihler.wiki.adapters.presentation.rest.inputs.WikiPageInput;
import com.zihler.wiki.application.outbound_ports.gateways.FindWikiPage;
import com.zihler.wiki.application.outbound_ports.gateways.StoreWikiPage;
import com.zihler.wiki.application.outbound_ports.presenters.WikiPagePresenter;
import com.zihler.wiki.application.use_cases.extend_wiki_article.ExtendWikiArticleUseCase;
import com.zihler.wiki.application.use_cases.extend_wiki_article.inbound_port.ExtendWikiArticle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExtendWikiArticleController {

    private final ExtendWikiArticle extendWikiArticle;

    @Autowired
    public ExtendWikiArticleController(FindWikiPage findWikiPage, StoreWikiPage storeWikiPage) {
        extendWikiArticle = new ExtendWikiArticleUseCase(findWikiPage, storeWikiPage);
    }

    public void extendWith(WikiPageDto jsonRequest, WikiPagePresenter output) {
        var input = new WikiPageInput(jsonRequest);

        extendWikiArticle.with(input.wikiPage(), output);
    }
}
