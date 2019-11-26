package com.zihler.wiki.use_case.outbound_ports;

import com.zihler.wiki.domain.entity.WikiPage;

public interface WikiPageRepository {
    WikiPage store(WikiPage wikiPage);
}
