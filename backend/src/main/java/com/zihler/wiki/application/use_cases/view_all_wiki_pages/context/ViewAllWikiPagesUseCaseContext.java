package com.zihler.wiki.application.use_cases.view_all_wiki_pages.context;

import com.zihler.wiki.application.outbound_ports.gateways.RetrieveAllWikiPages;
import com.zihler.wiki.application.use_cases.UseCaseContext;
import com.zihler.wiki.application.use_cases.view_all_wiki_pages.outbound_port.WikiPagesPresenter;
import com.zihler.wiki.application.use_cases.view_all_wiki_pages.roles.WikiPagesSearchResult;
import com.zihler.wiki.domain.values.WikiPages;

// TODO: 12/24/2019 Refactor WikiPagesSearchResult: its only "find all wiki pages"
public class ViewAllWikiPagesUseCaseContext implements UseCaseContext {
    private WikiPagesSearchResult searchResult;

    private ViewAllWikiPagesUseCaseContext(WikiPagesSearchResult searchResult) {
        this.searchResult = searchResult;
    }

    public static UseCaseContext initialize(RetrieveAllWikiPages retrieveAllWikiPages, WikiPagesPresenter presenter) {
        WikiPages self = retrieveAllWikiPages.get();
        WikiPagesSearchResult searchResult = new WikiPagesSearchResult(self, presenter);
        return new ViewAllWikiPagesUseCaseContext(searchResult);
    }

    @Override
    public void enactUseCase() {
        searchResult.present();
    }
}
