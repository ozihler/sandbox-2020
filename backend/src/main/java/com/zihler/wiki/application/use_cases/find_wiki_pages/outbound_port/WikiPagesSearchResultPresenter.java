package com.zihler.wiki.application.use_cases.find_wiki_pages.outbound_port;

import com.zihler.wiki.application.outbound_ports.documents.WikiPagesDocument;

public interface WikiPagesSearchResultPresenter {
    void present(WikiPagesDocument wikiPagesDocument);
}
