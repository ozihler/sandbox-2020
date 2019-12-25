package com.zihler.wiki.adapters.data_access.persistence.in_memory;

import com.zihler.wiki.application.outbound_ports.gateways.FindWikiPage;
import com.zihler.wiki.application.outbound_ports.gateways.RetrieveAllWikiPages;
import com.zihler.wiki.application.outbound_ports.gateways.StoreWikiPage;
import com.zihler.wiki.domain.entity.WikiPage;
import com.zihler.wiki.domain.values.ReferenceTag;
import com.zihler.wiki.domain.values.WikiPages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Profile("dev")
public class InMemoryWikiPageRepository implements FindWikiPage, RetrieveAllWikiPages, StoreWikiPage {
    private HashMapWikiPageRepository db;

    @Autowired
    public InMemoryWikiPageRepository(HashMapWikiPageRepository db) {
        this.db = db;
    }

    @Override
    public Optional<WikiPage> by(ReferenceTag referenceTag) {
        return db.findBy(referenceTag);
    }

    @Override
    public WikiPages get() {
        return db.findAll();
    }

    @Override
    public WikiPage as(WikiPage wikiPage) {
        return db.save(wikiPage);
    }
}
