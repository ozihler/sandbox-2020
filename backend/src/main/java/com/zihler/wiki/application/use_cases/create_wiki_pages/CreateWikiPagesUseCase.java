package com.zihler.wiki.application.use_cases.create_wiki_pages;

import com.zihler.wiki.application.use_cases.outbound_ports.gateway.FindWikiPage;
import com.zihler.wiki.application.use_cases.outbound_ports.gateway.StoreWikiPage;
import com.zihler.wiki.application.use_cases.outbound_ports.presenter.Presenter;
import com.zihler.wiki.application.use_cases.ports.CreateWikiPages;
import com.zihler.wiki.domain.entity.WikiPage;
import com.zihler.wiki.domain.values.*;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class CreateWikiPagesUseCase implements CreateWikiPages {
    private final FindWikiPage<ReferenceTag> findWikiPage;
    private final StoreWikiPage storeWikiPage;

    public CreateWikiPagesUseCase(FindWikiPage<ReferenceTag> findWikiPage, StoreWikiPage storeWikiPage) {
        this.findWikiPage = findWikiPage;
        this.storeWikiPage = storeWikiPage;
    }

    @Override
    public void from(Body body, Presenter<WikiPagesDocument> presenter) {
        ReferenceTags referenceTags = ReferenceTags.from(body);

        WikiPages wikiPages = findOrCreateFrom(referenceTags);

        presenter.present(WikiPagesDocument.of(wikiPages));
    }

    private WikiPages findOrCreateFrom(ReferenceTags referenceTags) {
        Set<WikiPage> wikiPages = new HashSet<>();

        for (ReferenceTag referenceTag : referenceTags.getReferenceTags()) {
            Optional<WikiPage> wikiPage = findWikiPage.by(referenceTag);
            wikiPages.add(wikiPage.orElseGet(() -> {
                Title title = Title.from(referenceTag);
                return storeWikiPage.as(WikiPage.from(referenceTag, title));
            }));
        }

        return new WikiPages(wikiPages);
    }
}
