package com.zihler.wiki.application.use_cases.create_wiki_pages;

import com.zihler.wiki.application.outbound_ports.gateway.FindWikiPageByReferenceTag;
import com.zihler.wiki.application.outbound_ports.gateway.StoreWikiPage;
import com.zihler.wiki.domain.entity.WikiPage;
import com.zihler.wiki.domain.values.Body;
import com.zihler.wiki.domain.values.ReferenceTags;
import com.zihler.wiki.domain.values.WikiPages;
import com.zihler.wiki.domain.values.WikiPagesDocument;

import java.util.LinkedHashSet;
import java.util.stream.Collectors;

class CreatedWikiPages {
    private final StoreWikiPage storeWikiPage;
    private WikiPages self;

    private CreatedWikiPages(WikiPages self, StoreWikiPage storeWikiPage) {
        this.self = self;
        this.storeWikiPage = storeWikiPage;
    }


    static CreatedWikiPages create(Body body, FindWikiPageByReferenceTag findWikiPage, StoreWikiPage storeWikiPage) {
        ReferenceTags referenceTags = ReferenceTags.from(body);

        WikiPages wikiPages = new WikiPages(create(findWikiPage, referenceTags));

        return new CreatedWikiPages(wikiPages, storeWikiPage);
    }

    private static LinkedHashSet<WikiPage> create(FindWikiPageByReferenceTag findWikiPage, ReferenceTags referenceTags) {
        return referenceTags.getReferenceTags()
                .stream()
                .filter(referenceTag -> !findWikiPage.existsWith(referenceTag))
                .map(WikiPage::from)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    void storeAll() {
        self = new WikiPages(storeWikiPages());
    }

    private LinkedHashSet<WikiPage> storeWikiPages() {
        return self.getWikiPages()
                .stream()
                .map(storeWikiPage::as)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    WikiPagesDocument toDocument() {
        return WikiPagesDocument.of(self);
    }
}
