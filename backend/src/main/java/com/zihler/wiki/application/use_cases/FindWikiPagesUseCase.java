package com.zihler.wiki.application.use_cases;

import com.zihler.wiki.application.use_cases.outbound_ports.IFindAllWikiPages;
import com.zihler.wiki.application.use_cases.outbound_ports.Presenter;
import com.zihler.wiki.application.use_cases.outbound_ports.WikiPagesSearchResultDocument;
import com.zihler.wiki.application.use_cases.ports.FindWikiPages;
import com.zihler.wiki.domain.values.search.WikiPagesSearchResult;

public class FindWikiPagesUseCase implements FindWikiPages {
    private IFindAllWikiPages findAllWikiPages;

    public FindWikiPagesUseCase(IFindAllWikiPages findAllWikiPages) {
        this.findAllWikiPages = findAllWikiPages;
    }

    @Override
    public void all(Presenter<WikiPagesSearchResultDocument> presenter) {
        WikiPagesSearchResult searchResult = findAllWikiPages.all();

        presenter.present(WikiPagesSearchResultDocument.from(searchResult));
    }
}
