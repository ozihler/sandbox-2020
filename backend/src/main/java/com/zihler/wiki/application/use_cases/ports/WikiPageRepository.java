package com.zihler.wiki.application.use_cases.ports;

import com.zihler.wiki.domain.entity.WikiPage;

public interface WikiPageRepository {
    WikiPage store(WikiPage wikiPage);
}
