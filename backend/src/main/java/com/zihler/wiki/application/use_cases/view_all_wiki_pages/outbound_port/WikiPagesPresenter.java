package com.zihler.wiki.application.use_cases.view_all_wiki_pages.outbound_port;

import com.zihler.wiki.application.outbound_ports.documents.WikiPagesDocument;

public interface WikiPagesPresenter {
    void present(WikiPagesDocument wikiPagesDocument);
}
