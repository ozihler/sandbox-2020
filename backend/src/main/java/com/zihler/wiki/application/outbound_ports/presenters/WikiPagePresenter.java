package com.zihler.wiki.application.outbound_ports.presenters;

import com.zihler.wiki.application.outbound_ports.documents.WikiPageDocument;

public interface WikiPagePresenter {
    void present(WikiPageDocument wikiPageDocument);
}
