package com.zihler.wiki.adapters.data_access.persistence.in_memory;

import com.zihler.wiki.domain.entity.WikiPage;
import com.zihler.wiki.domain.values.ReferenceTag;
import com.zihler.wiki.domain.values.WikiPages;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Optional;

import static java.util.Optional.empty;

@Component
public class HashMapWikiPageRepository {
    private HashMap<ReferenceTag, WikiPage> db;

    public HashMapWikiPageRepository() {
        db = new HashMap<>();
    }

    public Optional<WikiPage> findBy(ReferenceTag referenceTag) {
        if (!db.containsKey(referenceTag)) {
            return empty();
        } else {
            return Optional.of(db.get(referenceTag));
        }
    }

    WikiPages findAll() {
        return WikiPages.from(new LinkedHashSet<>(db.values()));
    }

    public WikiPage save(WikiPage wikiPage) {
        db.put(wikiPage.getReferenceTag(), wikiPage);
        return db.get(wikiPage.getReferenceTag());
    }
}
