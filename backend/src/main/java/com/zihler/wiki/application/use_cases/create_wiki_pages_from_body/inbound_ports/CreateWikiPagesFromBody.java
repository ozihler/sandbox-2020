package com.zihler.wiki.application.use_cases.create_wiki_pages_from_body.inbound_ports;

import com.zihler.wiki.application.outbound_ports.presenter.WikiPagePresenter;
import com.zihler.wiki.domain.values.Body;

public interface CreateWikiPagesFromBody {
    void from(Body body, WikiPagePresenter presenter);
}
