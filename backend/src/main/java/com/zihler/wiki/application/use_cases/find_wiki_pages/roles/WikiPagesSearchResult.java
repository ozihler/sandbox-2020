package com.zihler.wiki.application.use_cases.find_wiki_pages.roles;

import com.zihler.wiki.application.use_cases.create_multiple_wiki_pages.outbound_port.presenter.MultipleWikiPagesPresenter;
import com.zihler.wiki.domain.values.WikiPages;
import com.zihler.wiki.domain.values.WikiPagesDocument;

public class WikiPagesSearchResult {
    private WikiPages wikiPages;
    private MultipleWikiPagesPresenter presenter;

    public WikiPagesSearchResult(WikiPages wikiPages, MultipleWikiPagesPresenter presenter) {
        this.wikiPages = wikiPages;
        this.presenter = presenter;
    }

    public void present() {
        presenter.present(WikiPagesDocument.of(wikiPages));
    }
}
