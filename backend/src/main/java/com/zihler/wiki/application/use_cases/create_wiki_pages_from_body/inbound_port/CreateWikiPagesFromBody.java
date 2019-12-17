package com.zihler.wiki.application.use_cases.create_wiki_pages_from_body.inbound_port;

import com.zihler.wiki.application.outbound_ports.presenters.WikiPagePresenter;
import com.zihler.wiki.domain.values.Body;

public interface CreateWikiPagesFromBody {
    void callWith(Body body, WikiPagePresenter presenter);
}
