package com.zihler.wiki.application.use_cases.extend_wiki_article.inbound_port;

import com.zihler.wiki.application.outbound_ports.documents.WikiPageDocument;
import com.zihler.wiki.application.outbound_ports.presenters.WikiPagePresenter;

public interface ExtendWikiArticle {
    void with(WikiPageDocument extendedWikiPage, WikiPagePresenter presenter);
}
