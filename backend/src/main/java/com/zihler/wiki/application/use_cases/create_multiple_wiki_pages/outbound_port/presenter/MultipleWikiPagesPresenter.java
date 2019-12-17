package com.zihler.wiki.application.use_cases.create_multiple_wiki_pages.outbound_port.presenter;

import com.zihler.wiki.application.use_cases.create_single_wiki_page.outbound_port.presenter.SingleWikiPagePresenter;
import com.zihler.wiki.domain.values.WikiPagesDocument;

public interface MultipleWikiPagesPresenter extends SingleWikiPagePresenter {
    void present(WikiPagesDocument wikiPagesDocument);
}
