package com.zihler.wiki.application.use_cases.ports;

import com.zihler.wiki.application.use_cases.outbound_ports.presenter.Presenter;
import com.zihler.wiki.domain.values.Body;
import com.zihler.wiki.domain.values.WikiPagesDocument;

public interface CreateWikiPages {
    void from(Body body, Presenter<WikiPagesDocument> presenter);
}
