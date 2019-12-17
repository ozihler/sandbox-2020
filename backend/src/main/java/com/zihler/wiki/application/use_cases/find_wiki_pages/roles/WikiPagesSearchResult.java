package com.zihler.wiki.application.use_cases.find_wiki_pages.roles;

import com.zihler.wiki.application.outbound_ports.documents.WikiPagesDocument;
import com.zihler.wiki.application.use_cases.find_wiki_pages.outbound_port.WikiPagesSearchResultPresenter;
import com.zihler.wiki.domain.values.WikiPages;

public class WikiPagesSearchResult {
    private WikiPages wikiPages;
    private WikiPagesSearchResultPresenter presenter;

    public WikiPagesSearchResult(WikiPages wikiPages, WikiPagesSearchResultPresenter presenter) {
        this.wikiPages = wikiPages;
        this.presenter = presenter;
    }

    public void present() {
        presenter.present(WikiPagesDocument.of(wikiPages));
    }
}
