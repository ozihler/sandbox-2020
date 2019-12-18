package com.zihler.wiki.application.use_cases.create_wiki_pages_from_body.inbound_port;

import com.zihler.wiki.application.outbound_ports.documents.WikiPageDocument;
import com.zihler.wiki.application.outbound_ports.presenters.WikiPagePresenter;

public interface ExtendWikiArticle {
    void with(WikiPageDocument extendedWikiPage, WikiPagePresenter presenter);
}
