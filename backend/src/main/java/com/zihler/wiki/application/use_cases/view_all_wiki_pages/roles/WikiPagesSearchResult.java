package com.zihler.wiki.application.use_cases.view_all_wiki_pages.roles;

import com.zihler.wiki.application.outbound_ports.documents.WikiPagesDocument;
import com.zihler.wiki.application.use_cases.view_all_wiki_pages.outbound_port.WikiPagesPresenter;
import com.zihler.wiki.domain.values.WikiPages;

public class WikiPagesSearchResult {
    private WikiPages wikiPages;
    private WikiPagesPresenter presenter;

    public WikiPagesSearchResult(WikiPages wikiPages, WikiPagesPresenter presenter) {
        this.wikiPages = wikiPages;
        this.presenter = presenter;
    }

    public void present() {
        presenter.present(WikiPagesDocument.of(wikiPages));
    }
}
