package com.zihler.wiki.application.habits.create_wiki_page.inbound_ports;

import com.zihler.wiki.application.outbound_ports.documents.WikiPageDocument;
import com.zihler.wiki.application.outbound_ports.presenter.WikiPagePresenter;

public interface CreateWikiPage {
    void with(WikiPageDocument intendedWikiPage, WikiPagePresenter presenter);
}
