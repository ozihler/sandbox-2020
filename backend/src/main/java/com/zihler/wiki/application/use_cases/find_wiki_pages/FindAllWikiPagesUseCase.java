package com.zihler.wiki.application.use_cases.find_wiki_pages;

import com.zihler.wiki.application.use_cases.outbound_ports.documents.WikiPagesSearchResultDocument;
import com.zihler.wiki.application.use_cases.outbound_ports.gateway.RetrieveAllWikiPages;
import com.zihler.wiki.application.use_cases.outbound_ports.presenter.Presenter;
import com.zihler.wiki.application.use_cases.ports.FindAllWikiPages;
import com.zihler.wiki.domain.values.search.WikiPagesSearchResult;

public class FindAllWikiPagesUseCase implements FindAllWikiPages {
    private RetrieveAllWikiPages retrieveAllWikiPages;

    public FindAllWikiPagesUseCase(RetrieveAllWikiPages retrieveAllWikiPages) {
        this.retrieveAllWikiPages = retrieveAllWikiPages;
    }

    @Override
    public void callWith(Presenter<WikiPagesSearchResultDocument> presenter) {
        WikiPagesSearchResult searchResult = retrieveAllWikiPages.get();

        presenter.present(WikiPagesSearchResultDocument.from(searchResult));
    }
}
