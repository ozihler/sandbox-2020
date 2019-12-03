package com.zihler.wiki.application.use_cases.ports;

import com.zihler.wiki.application.use_cases.outbound_ports.Presenter;
import com.zihler.wiki.application.use_cases.outbound_ports.WikiPageDocument;
import com.zihler.wiki.domain.values.Title;

public interface CreateWikiPageFromTitle {
    void from(Title title, Presenter<WikiPageDocument> presenter);
}
