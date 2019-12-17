package com.zihler.wiki.application.use_cases.find_wiki_pages.roles;

import com.zihler.wiki.application.outbound_ports.presenter.Presenter;
import com.zihler.wiki.domain.values.WikiPages;
import com.zihler.wiki.domain.values.WikiPagesDocument;

public class WikiPagesSearchResult {
    private WikiPages wikiPages;
    private Presenter<WikiPagesDocument> presenter;

    public WikiPagesSearchResult(WikiPages wikiPages, Presenter<WikiPagesDocument> presenter) {
        this.wikiPages = wikiPages;
        this.presenter = presenter;
    }

    public void present() {
        presenter.present(WikiPagesDocument.of(wikiPages));
    }
}
