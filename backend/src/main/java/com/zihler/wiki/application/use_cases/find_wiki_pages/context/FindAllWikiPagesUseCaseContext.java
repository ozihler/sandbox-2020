package com.zihler.wiki.application.use_cases.find_wiki_pages.context;

import com.zihler.wiki.application.outbound_ports.gateway.RetrieveAllWikiPages;
import com.zihler.wiki.application.use_cases.UseCaseContext;
import com.zihler.wiki.application.use_cases.find_wiki_pages.outbound_port.WikiPagesSearchResultPresenter;
import com.zihler.wiki.application.use_cases.find_wiki_pages.roles.WikiPagesSearchResult;

public class FindAllWikiPagesUseCaseContext implements UseCaseContext {
    private WikiPagesSearchResult searchResult;

    private FindAllWikiPagesUseCaseContext(WikiPagesSearchResult searchResult) {
        this.searchResult = searchResult;
    }

    public static UseCaseContext initialize(RetrieveAllWikiPages retrieveAllWikiPages, WikiPagesSearchResultPresenter presenter) {
        var self = retrieveAllWikiPages.get();
        var searchResult = new WikiPagesSearchResult(self, presenter);
        return new FindAllWikiPagesUseCaseContext(searchResult);
    }

    @Override
    public void enactUseCase() {
        searchResult.present();
    }
}
