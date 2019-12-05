package com.zihler.wiki.application.use_cases.outbound_ports.gateway;

import com.zihler.wiki.domain.entity.WikiPage;

public interface StoreWikiPage {
    WikiPage as(WikiPage wikiPage);
}
