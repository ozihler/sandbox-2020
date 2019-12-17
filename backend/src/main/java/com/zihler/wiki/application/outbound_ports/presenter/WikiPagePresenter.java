package com.zihler.wiki.application.outbound_ports.presenter;

import com.zihler.wiki.application.outbound_ports.documents.WikiPageDocument;

public interface WikiPagePresenter {
    void present(WikiPageDocument wikiPageDocument);
}
