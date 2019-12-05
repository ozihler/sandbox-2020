package com.zihler.wiki.application.use_cases.outbound_ports.gateway;

import com.zihler.wiki.domain.values.search.WikiPagesSearchResult;

public interface FindAllWikiPages {
    WikiPagesSearchResult all();
}
