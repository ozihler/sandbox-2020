package com.zihler.wiki.adapters.data_access.persistence.hibernate;

import com.zihler.wiki.adapters.data_access.persistence.hibernate.framework.JpaWikiPageRepository;
import com.zihler.wiki.adapters.data_access.persistence.hibernate.framework.WikiPageTable;
import com.zihler.wiki.application.outbound_ports.gateways.FindWikiPage;
import com.zihler.wiki.application.outbound_ports.gateways.StoreWikiPage;
import com.zihler.wiki.application.use_cases.find_wiki_pages.inbound_port.FindAllWikiPages;
import com.zihler.wiki.application.use_cases.find_wiki_pages.outbound_port.WikiPagesSearchResultPresenter;
import com.zihler.wiki.domain.entity.WikiPage;
import com.zihler.wiki.domain.values.Body;
import com.zihler.wiki.domain.values.ReferenceTag;
import com.zihler.wiki.domain.values.ReferencedWikiPages;
import com.zihler.wiki.domain.values.Title;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

@Repository
public class SqlWikiPageRepository implements StoreWikiPage, FindWikiPage, FindAllWikiPages {

    private JpaWikiPageRepository db;

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
        return null;
    }

    @Override
    public void andSendThemTo(WikiPagesSearchResultPresenter presenter) {

    }
}
