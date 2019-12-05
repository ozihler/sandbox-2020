package com.zihler.wiki.adapters.data_access.in_memory;

import com.zihler.wiki.application.use_cases.outbound_ports.gateway.FindAllWikiPages;
import com.zihler.wiki.application.use_cases.outbound_ports.gateway.FindWikiPage;
import com.zihler.wiki.application.use_cases.outbound_ports.gateway.StoreWikiPage;
import com.zihler.wiki.domain.entity.WikiPage;
import com.zihler.wiki.domain.values.ReferenceTag;
import com.zihler.wiki.domain.values.Title;
import com.zihler.wiki.domain.values.search.WikiPagesSearchResult;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Optional;
import java.util.TreeSet;

import static java.util.Optional.empty;
import static java.util.Optional.of;

@Repository
public class InMemoryWikiPageRepository implements FindWikiPage, StoreWikiPage, FindAllWikiPages {
    private HashMap<ReferenceTag, WikiPage> db = new HashMap<>();

    @Override
    public WikiPage as(WikiPage wikiPage) {
        db.put(wikiPage.getReferenceTag(), wikiPage);
        return db.get(wikiPage.getReferenceTag());
    }

    @Override
    public WikiPagesSearchResult all() {
        return WikiPagesSearchResult.from(new TreeSet<>(db.values()));
    }

    @Override
    public Optional<WikiPage> by(Object object) {
        if (object instanceof ReferenceTag) {
            return findBy((ReferenceTag) object);
        } else if (object instanceof Title) {
            return findByTitle((Title) object);
        } else {
            return Optional.empty();
        }
    }

    private Optional<WikiPage> findByTitle(Title title) {
        ReferenceTag key = ReferenceTag.from(title);

        var wikiPage = db.get(key);

        if (wikiPage == null) {
            return empty();
        } else {
            return of(wikiPage);
        }
    }

    private Optional<WikiPage> findBy(ReferenceTag referenceTag) {
        if (!db.containsKey(referenceTag)) {
            return empty();
        } else {
            return Optional.of(db.get(referenceTag));
        }
    }

}
