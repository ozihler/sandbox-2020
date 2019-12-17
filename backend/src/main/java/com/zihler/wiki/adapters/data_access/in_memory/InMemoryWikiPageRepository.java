package com.zihler.wiki.adapters.data_access.in_memory;

import com.zihler.wiki.application.outbound_ports.gateway.FindWikiPage;
import com.zihler.wiki.application.outbound_ports.gateway.RetrieveAllWikiPages;
import com.zihler.wiki.application.outbound_ports.gateway.StoreWikiPage;
import com.zihler.wiki.domain.entity.WikiPage;
import com.zihler.wiki.domain.values.ReferenceTag;
import com.zihler.wiki.domain.values.WikiPages;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Optional;

import static java.util.Optional.empty;

@Repository
public class InMemoryWikiPageRepository implements FindWikiPage, RetrieveAllWikiPages, StoreWikiPage {
    private HashMap<ReferenceTag, WikiPage> db = new HashMap<>();

    @Override
    public Optional<WikiPage> by(ReferenceTag referenceTag) {
        if (!db.containsKey(referenceTag)) {
            return empty();
        } else {
            return Optional.of(db.get(referenceTag));
        }
    }

    @Override
    public WikiPages get() {
        return new WikiPages(new LinkedHashSet<>(db.values()));

    }

    @Override
    public WikiPage as(WikiPage wikiPage) {
        db.put(wikiPage.getReferenceTag(), wikiPage);
        return db.get(wikiPage.getReferenceTag());
    }
}
