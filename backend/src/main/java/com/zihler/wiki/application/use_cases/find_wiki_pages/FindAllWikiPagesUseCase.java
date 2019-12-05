package com.zihler.wiki.application.use_cases.find_wiki_pages;

import com.zihler.wiki.application.use_cases.outbound_ports.documents.WikiPagesSearchResultDocument;
import com.zihler.wiki.application.use_cases.outbound_ports.gateway.FindAllWikiPages;
import com.zihler.wiki.application.use_cases.outbound_ports.presenter.Presenter;
import com.zihler.wiki.domain.values.search.WikiPagesSearchResult;

public class FindAllWikiPagesUseCase implements com.zihler.wiki.application.use_cases.ports.FindAllWikiPages {
    private FindAllWikiPages findAllWikiPages;

    public FindAllWikiPagesUseCase(FindAllWikiPages findAllWikiPages) {
        this.findAllWikiPages = findAllWikiPages;
    }

    @Override
    public void callWith(Presenter<WikiPagesSearchResultDocument> presenter) {
        WikiPagesSearchResult searchResult = findAllWikiPages.all();

        presenter.present(WikiPagesSearchResultDocument.from(searchResult));
    }
}
