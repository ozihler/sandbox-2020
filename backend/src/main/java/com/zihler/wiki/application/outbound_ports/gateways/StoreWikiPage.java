package com.zihler.wiki.application.outbound_ports.gateways;

import com.zihler.wiki.domain.entity.WikiPage;

public interface StoreWikiPage {
    WikiPage as(WikiPage wikiPage);
}
