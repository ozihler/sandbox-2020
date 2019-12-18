package com.zihler.wiki.application.use_cases.find_wiki_pages.context;

import com.zihler.wiki.application.outbound_ports.gateways.RetrieveAllWikiPages;
import com.zihler.wiki.application.use_cases.UseCaseContext;
import com.zihler.wiki.application.use_cases.find_wiki_pages.outbound_port.WikiPagesSearchResultPresenter;
import com.zihler.wiki.application.use_cases.find_wiki_pages.roles.WikiPagesSearchResult;
import com.zihler.wiki.domain.values.WikiPages;

public class FindAllWikiPagesUseCaseContext implements UseCaseContext {
    private WikiPagesSearchResult searchResult;

    private FindAllWikiPagesUseCaseContext(WikiPagesSearchResult searchResult) {
        this.searchResult = searchResult;
    }

    public static UseCaseContext initialize(RetrieveAllWikiPages retrieveAllWikiPages, WikiPagesSearchResultPresenter presenter) {
        WikiPages self = retrieveAllWikiPages.get();
        WikiPagesSearchResult searchResult = new WikiPagesSearchResult(self, presenter);
        return new FindAllWikiPagesUseCaseContext(searchResult);
    }

    @Override
    public void enactUseCase() {
        searchResult.present();
    }
}
