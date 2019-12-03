package com.zihler.wiki.application.use_cases.ports;

import com.zihler.wiki.application.use_cases.outbound_ports.Presenter;
import com.zihler.wiki.application.use_cases.outbound_ports.WikiPagesSearchResultDocument;

public interface FindWikiPages {
    void all(Presenter<WikiPagesSearchResultDocument> presenter);
}
