package com.zihler.wiki.application.use_cases.create_wiki_pages_from_body.inbound_port;

import com.zihler.wiki.application.outbound_ports.documents.BodyDocument;
import com.zihler.wiki.application.outbound_ports.presenters.WikiPagePresenter;

public interface CreateWikiPagesFromBody {
    void callWith(BodyDocument body, WikiPagePresenter presenter);
}
