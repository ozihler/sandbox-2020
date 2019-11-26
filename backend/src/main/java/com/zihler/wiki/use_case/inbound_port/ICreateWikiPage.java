package com.zihler.wiki.use_case.inbound_port;

import com.zihler.wiki.domain.values.Details;
import com.zihler.wiki.use_case.outbound_ports.WikiPagePresenter;

public interface ICreateWikiPage {
    void withDetails(Details details, WikiPagePresenter wikiPagePresenter);
}
