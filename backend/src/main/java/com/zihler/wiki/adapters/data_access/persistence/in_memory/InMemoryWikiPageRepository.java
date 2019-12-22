package com.zihler.wiki.adapters.data_access.persistence.in_memory;

import com.zihler.wiki.application.outbound_ports.gateways.FindWikiPage;
import com.zihler.wiki.application.outbound_ports.gateways.RetrieveAllWikiPages;
import com.zihler.wiki.application.outbound_ports.gateways.StoreWikiPage;
import com.zihler.wiki.domain.entity.WikiPage;
import com.zihler.wiki.domain.values.ReferenceTag;
import com.zihler.wiki.domain.values.WikiPages;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Optional;

import static java.util.Optional.empty;

@Repository
@Profile("dev")
public class InMemoryWikiPageRepository implements FindWikiPage, RetrieveAllWikiPages, StoreWikiPage {
    private HashMap<ReferenceTag, WikiPage> db;

    public InMemoryWikiPageRepository() {
        db = new HashMap<>();
    }

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
        return WikiPages.from(new LinkedHashSet<>(db.values()));

    }

    @Override
    public WikiPage as(WikiPage wikiPage) {
        db.put(wikiPage.getReferenceTag(), wikiPage);
        return db.get(wikiPage.getReferenceTag());
    }
}
