package com.zihler.wiki.application.use_cases.update_wiki_page.inbound_port;

import com.zihler.wiki.application.outbound_ports.documents.WikiPageDocument;
import com.zihler.wiki.application.outbound_ports.presenters.WikiPagePresenter;

public interface UpdateWikiPage {
    void callWith(WikiPageDocument wikiPage, WikiPagePresenter output);
}
