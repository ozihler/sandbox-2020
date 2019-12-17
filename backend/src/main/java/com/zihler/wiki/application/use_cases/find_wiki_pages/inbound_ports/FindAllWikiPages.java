package com.zihler.wiki.application.use_cases.find_wiki_pages.inbound_ports;

import com.zihler.wiki.application.outbound_ports.presenter.Presenter;
import com.zihler.wiki.domain.values.WikiPagesDocument;

public interface FindAllWikiPages {
    void callWith(Presenter<WikiPagesDocument> presenter);
}
