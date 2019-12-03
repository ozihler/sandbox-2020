package com.zihler.wiki.adapters.data_access.in_memory;

import com.zihler.wiki.application.use_cases.outbound_ports.IFindAllWikiPages;
import com.zihler.wiki.application.use_cases.outbound_ports.IFindWikiPagesByTitle;
import com.zihler.wiki.application.use_cases.outbound_ports.IStoreWikiPages;
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
public class InMemoryWikiPageRepositoryByTitle implements
        IFindWikiPagesByTitle,
        IStoreWikiPages,
        IFindAllWikiPages {
    private HashMap<ReferenceTag, WikiPage> db = new HashMap<>();

    @Override
    public Optional<WikiPage> findFor(Title title) {
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
        db.put(wikiPage.getReferenceTag(), wikiPage);
        return db.get(wikiPage.getReferenceTag());
    }

    @Override
    public WikiPagesSearchResult all() {
        return WikiPagesSearchResult.from(new TreeSet<>(db.values()));
    }
}
