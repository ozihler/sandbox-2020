package com.zihler.wiki.application.use_cases.find_wiki_pages.outbound_port;

import com.zihler.wiki.domain.values.WikiPagesDocument;

public interface WikiPagesSearchResultPresenter {
    void present(WikiPagesDocument wikiPagesDocument);
}
