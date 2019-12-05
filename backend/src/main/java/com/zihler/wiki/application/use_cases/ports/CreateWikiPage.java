package com.zihler.wiki.application.use_cases.ports;

import com.zihler.wiki.application.use_cases.outbound_ports.documents.WikiPageDocument;
import com.zihler.wiki.application.use_cases.outbound_ports.presenter.Presenter;
import com.zihler.wiki.domain.values.Title;

public interface CreateWikiPage {
    void from(Title title, Presenter<WikiPageDocument> presenter);
}
