package com.zihler.wiki.application.use_cases.create_single_wiki_page.outbound_port.presenter;

import com.zihler.wiki.application.outbound_ports.documents.WikiPageDocument;

public interface SingleWikiPagePresenter {
    void present(WikiPageDocument wikiPageDocument);
}
