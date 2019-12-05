package com.zihler.wiki.application.use_cases.ports;

import com.zihler.wiki.application.use_cases.outbound_ports.documents.WikiPagesSearchResultDocument;
import com.zihler.wiki.application.use_cases.outbound_ports.presenter.Presenter;

public interface FindAllWikiPages {
    void callWith(Presenter<WikiPagesSearchResultDocument> presenter);
}
