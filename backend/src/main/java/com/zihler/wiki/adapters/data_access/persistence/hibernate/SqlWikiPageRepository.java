package com.zihler.wiki.adapters.data_access.persistence.hibernate;

import com.zihler.wiki.adapters.data_access.persistence.hibernate.framework.JpaWikiPageRepository;
import com.zihler.wiki.adapters.data_access.persistence.hibernate.framework.WikiPageRow;
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

    private WikiPage toWikiPage(WikiPageRow rowData) {
        return WikiPage.from(
                ReferenceTag.from(rowData.getReferenceTag()),
                Title.from(rowData.getTitle()),
                Body.from(rowData.getBody()),
                ReferencedWikiPages.from(referenceTagsFrom(rowData.getReferencedWikiPages()))
        );
    }

    private Set<ReferenceTag> referenceTagsFrom(Set<WikiPageRow> referencedWikiPages) {
        return referencedWikiPages.stream().map(WikiPageRow::getReferenceTag).map(ReferenceTag::from).collect(toSet());
    }

    @Override
    public WikiPage as(WikiPage wikiPage) {
        return toWikiPage(db.save(toDatabaseRow(wikiPage)));
    }

    private WikiPageRow toDatabaseRow(WikiPage wikiPage) {
        WikiPageRow wikiPageRow = new WikiPageRow();
        wikiPageRow.setReferenceTag(wikiPage.getReferenceTag().toString());
        wikiPageRow.setTitle(wikiPage.getTitle().toString());
        wikiPageRow.setBody(wikiPage.getBody().toString());
        wikiPageRow.setReferencedWikiPages(existingWikiPages(wikiPage));

        return wikiPageRow;
    }

    private Set<WikiPageRow> assureAllWikiPagesExist(Set<String> referencedWikiPagesTagStrings, Set<WikiPageRow> existingWikiPages) {
        if (containsUnknownReferenceTags(referencedWikiPagesTagStrings, existingWikiPagesTagStrings(existingWikiPages))) {
            throw new IllegalStateException("Some wiki pages were not created yet: " + referencedWikiPagesTagStrings);
        }
        return existingWikiPages;
    }

    private Set<String> existingWikiPagesTagStrings(Set<WikiPageRow> existingWikiPages) {
        return existingWikiPages.stream().map(WikiPageRow::getReferenceTag).collect(toSet());
    }

    private boolean containsUnknownReferenceTags(Set<String> referencedWikiPagesTagStrings, Set<String> existingWikiPagesTagStrings) {
        return referencedWikiPagesTagStrings.stream().anyMatch(e -> !existingWikiPagesTagStrings.contains(e));
    }

    private Set<WikiPageRow> existingWikiPages(WikiPage wikiPage) {
        Set<String> referencedWikiPagesTagStrings = referencedWikiPagesTagStrings(wikiPage);

        Set<WikiPageRow> existingWikiPages = referencedWikiPages(referencedWikiPagesTagStrings)
                .stream()
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(toSet());

        return assureAllWikiPagesExist(referencedWikiPagesTagStrings, existingWikiPages);
    }

    private Set<Optional<WikiPageRow>> referencedWikiPages(Set<String> referencedWikiPagesTagStrings) {
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
        Iterable<WikiPageRow> allRows = db.findAll();
        Set<WikiPage> wikiPages = toWikiPages(allRows);
        return WikiPages.from(wikiPages);
    }

    private Set<WikiPage> toWikiPages(Iterable<WikiPageRow> allRows) {
        return stream(allRows.spliterator(), false)
                .map(this::toWikiPage)
                .collect(toSet());
    }
}
