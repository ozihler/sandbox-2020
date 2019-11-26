package com.zihler.wiki.adapters.data_access.in_memory;

import com.zihler.wiki.domain.entity.WikiPage;
import com.zihler.wiki.domain.values.Id;
import com.zihler.wiki.use_case.outbound_ports.WikiPageRepository;

public class InMemoryWikiPageRepository implements WikiPageRepository {
    @Override
    public WikiPage store(WikiPage wikiPage) {
        return new WikiPage(Id.of(1L), wikiPage.getDetails());
    }
}
