package com.zihler.wiki.adapters.data_access.persistence.hibernate;

import com.zihler.wiki.adapters.data_access.persistence.hibernate.framework.JpaWikiPageRepository;
import com.zihler.wiki.adapters.data_access.persistence.hibernate.framework.WikiPageTable;
import com.zihler.wiki.application.outbound_ports.gateways.FindWikiPage;
import com.zihler.wiki.application.outbound_ports.gateways.RetrieveAllWikiPages;
import com.zihler.wiki.application.outbound_ports.gateways.StoreWikiPage;
import com.zihler.wiki.domain.entity.WikiPage;
import com.zihler.wiki.domain.values.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

import static java.util.stream.Collectors.toSet;
import static java.util.stream.StreamSupport.stream;

@Repository
@Profile({"test", "prod"})
public class SqlWikiPageRepository implements StoreWikiPage, FindWikiPage, RetrieveAllWikiPages {

    private JpaWikiPageRepository db;

    @Autowired
    public SqlWikiPageRepository(JpaWikiPageRepository db) {
        this.db = db;
    }

    @Override
    public Optional<WikiPage> by(ReferenceTag referenceTag) {
        return db.findById(referenceTag.toString()).map(this::toWikiPage);
    }

    private WikiPage toWikiPage(WikiPageTable rowData) {
        return WikiPage.from(
                ReferenceTag.from(rowData.getReferenceTag()),
                Title.from(rowData.getTitle()),
                Body.from(rowData.getBody()),
                ReferencedWikiPages.from(referenceTagsFrom(rowData.getReferencedWikiPages()))
        );

    }

    private Set<ReferenceTag> referenceTagsFrom(Set<WikiPageTable> referencedWikiPages) {
        return referencedWikiPages.stream().map(WikiPageTable::getReferenceTag).map(ReferenceTag::from).collect(toSet());
    }

    @Override
    public WikiPage as(WikiPage wikiPage) {
        return toWikiPage(db.save(toDatabaseRow(wikiPage)));
    }

    private WikiPageTable toDatabaseRow(WikiPage wikiPage) {
        WikiPageTable wikiPageTable = new WikiPageTable();
        wikiPageTable.setReferenceTag(wikiPage.getReferenceTag().toString());
        wikiPageTable.setTitle(wikiPage.getTitle().toString());
        wikiPageTable.setBody(wikiPage.getBody().toString());
        wikiPageTable.setReferencedWikiPages(existingWikiPages(wikiPage));

        return wikiPageTable;
    }

    private Set<WikiPageTable> assureAllWikiPagesExist(Set<String> referencedWikiPagesTagStrings, Set<WikiPageTable> existingWikiPages) {
        if (containsUnknownReferenceTags(referencedWikiPagesTagStrings, existingWikiPagesTagStrings(existingWikiPages))) {
            throw new IllegalStateException("Some wiki pages were not created yet: " + referencedWikiPagesTagStrings);
        }
        return existingWikiPages;
    }

    private Set<String> existingWikiPagesTagStrings(Set<WikiPageTable> existingWikiPages) {
        return existingWikiPages.stream().map(WikiPageTable::getReferenceTag).collect(toSet());
    }

    private boolean containsUnknownReferenceTags(Set<String> referencedWikiPagesTagStrings, Set<String> existingWikiPagesTagStrings) {
        return referencedWikiPagesTagStrings.stream().anyMatch(e -> !existingWikiPagesTagStrings.contains(e));
    }

    private Set<WikiPageTable> existingWikiPages(WikiPage wikiPage) {
        Set<String> referencedWikiPagesTagStrings = referencedWikiPagesTagStrings(wikiPage);

        Set<WikiPageTable> existingWikiPages = referencedWikiPages(referencedWikiPagesTagStrings)
                .stream()
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(toSet());

        return assureAllWikiPagesExist(referencedWikiPagesTagStrings, existingWikiPages);
    }

    private Set<Optional<WikiPageTable>> referencedWikiPages(Set<String> referencedWikiPagesTagStrings) {
        return referencedWikiPagesTagStrings
                .stream()
                .map(tag -> db.findById(tag))
                .collect(toSet());
    }

    private Set<String> referencedWikiPagesTagStrings(WikiPage wikiPage) {
        return wikiPage
                .getReferencedWikiPages()
                .getReferencedWikiPages().stream()
                .map(ReferenceTag::toString)
                .collect(toSet());
    }

    @Override
    public WikiPages get() {
        Set<WikiPage> wikiPages = stream(db.findAll().spliterator(), false)
                .map(this::toWikiPage)
                .collect(toSet());

        return WikiPages.from(wikiPages);
    }
}
