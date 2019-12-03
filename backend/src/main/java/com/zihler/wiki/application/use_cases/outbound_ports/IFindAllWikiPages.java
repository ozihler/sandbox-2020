package com.zihler.wiki.application.use_cases.outbound_ports;

import com.zihler.wiki.domain.values.search.WikiPagesSearchResult;

public interface IFindAllWikiPages {
    WikiPagesSearchResult all();
}
