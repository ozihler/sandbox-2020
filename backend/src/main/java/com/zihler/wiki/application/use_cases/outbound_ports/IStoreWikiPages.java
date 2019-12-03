package com.zihler.wiki.application.use_cases.outbound_ports;

import com.zihler.wiki.domain.entity.WikiPage;

public interface IStoreWikiPages {
    WikiPage store(WikiPage wikiPage);
}
