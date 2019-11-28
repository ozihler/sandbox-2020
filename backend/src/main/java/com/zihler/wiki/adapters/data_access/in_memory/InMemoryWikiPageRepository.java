package com.zihler.wiki.adapters.data_access.in_memory;

import com.zihler.wiki.application.use_cases.ports.WikiPageRepository;
import com.zihler.wiki.domain.entity.WikiPage;
import com.zihler.wiki.domain.values.Id;

public class InMemoryWikiPageRepository implements WikiPageRepository {
    @Override
    public WikiPage store(WikiPage wikiPage) {
        return new WikiPage(Id.of(1L), wikiPage.getDetails(), wikiPage.getReferencedWikiPages());
    }
}
