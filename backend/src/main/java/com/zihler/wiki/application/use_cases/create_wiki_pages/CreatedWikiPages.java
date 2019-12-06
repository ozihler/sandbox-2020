package com.zihler.wiki.application.use_cases.create_wiki_pages;

import com.zihler.wiki.application.use_cases.outbound_ports.gateway.FindWikiPage;
import com.zihler.wiki.application.use_cases.outbound_ports.gateway.StoreWikiPage;
import com.zihler.wiki.domain.entity.WikiPage;
import com.zihler.wiki.domain.values.*;

import java.util.Set;
import java.util.stream.Collectors;

class CreatedWikiPages {
    private final StoreWikiPage storeWikiPage;
    private WikiPages self;

    private CreatedWikiPages(WikiPages self, StoreWikiPage storeWikiPage) {
        this.self = self;
        this.storeWikiPage = storeWikiPage;
    }


    static CreatedWikiPages create(Body body, FindWikiPage<ReferenceTag> findWikiPage, StoreWikiPage storeWikiPage) {
        ReferenceTags referenceTags = ReferenceTags.from(body);

        WikiPages wikiPages = new WikiPages(create(findWikiPage, referenceTags));

        return new CreatedWikiPages(wikiPages, storeWikiPage);
    }

    private static Set<WikiPage> create(FindWikiPage<ReferenceTag> findWikiPage, ReferenceTags referenceTags) {
        return referenceTags.getReferenceTags()
                .stream()
                .filter(referenceTag -> !findWikiPage.having(referenceTag))
                .map(WikiPage::from)
                .collect(Collectors.toSet());
    }

    void storeAll() {
        this.self = new WikiPages(storeWikiPages());
    }

    private Set<WikiPage> storeWikiPages() {
        return self.getWikiPages()
                .stream()
                .map(storeWikiPage::as)
                .collect(Collectors.toSet());
    }

    WikiPagesDocument toDocument() {
        return WikiPagesDocument.of(self);
    }
}
