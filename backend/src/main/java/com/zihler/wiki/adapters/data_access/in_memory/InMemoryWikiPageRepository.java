package com.zihler.wiki.adapters.data_access.in_memory;

import com.zihler.wiki.application.use_cases.outbound_ports.IFindWikiPages;
import com.zihler.wiki.application.use_cases.outbound_ports.IStoreWikiPages;
import com.zihler.wiki.domain.entity.WikiPage;
import com.zihler.wiki.domain.values.ReferenceTag;
import com.zihler.wiki.domain.values.Title;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Optional;

import static java.util.Optional.empty;
import static java.util.Optional.of;

@Repository
public class InMemoryWikiPageRepository implements IFindWikiPages, IStoreWikiPages {
    private HashMap<ReferenceTag, WikiPage> db = new HashMap<>();

    @Override
    public Optional<WikiPage> findBy(Title title) {
        ReferenceTag key = ReferenceTag.from(title);

        var wikiPage = db.get(key);

        if (wikiPage == null) {
            return empty();
        } else {
            return of(wikiPage);
        }
    }

    @Override
    public WikiPage store(WikiPage wikiPage) {
        return db.put(wikiPage.getReferenceTag(), wikiPage);
    }
}
