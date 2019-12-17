package com.zihler.wiki.application.use_cases.find_wiki_pages;

import com.zihler.wiki.application.outbound_ports.gateway.RetrieveAllWikiPages;
import com.zihler.wiki.application.outbound_ports.presenter.Presenter;
import com.zihler.wiki.application.use_cases.Context;
import com.zihler.wiki.application.use_cases.find_wiki_pages.roles.WikiPagesSearchResult;
import com.zihler.wiki.domain.values.WikiPagesDocument;

public class FindAllWikiPagesContext implements Context {
    private WikiPagesSearchResult searchResult;

    public FindAllWikiPagesContext(WikiPagesSearchResult searchResult) {
        this.searchResult = searchResult;
    }

    public static Context initialize(RetrieveAllWikiPages retrieveAllWikiPages, Presenter<WikiPagesDocument> presenter) {
        var self = retrieveAllWikiPages.get();
        var searchResult = new WikiPagesSearchResult(self, presenter);
        return new FindAllWikiPagesContext(searchResult);
    }

    @Override
    public void enactUseCase() {
        searchResult.present();
    }

}
