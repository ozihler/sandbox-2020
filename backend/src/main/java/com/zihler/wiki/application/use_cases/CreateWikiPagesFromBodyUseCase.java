package com.zihler.wiki.application.use_cases;

import com.zihler.wiki.application.use_cases.outbound_ports.IFindWikiPagesByReferenceTag;
import com.zihler.wiki.application.use_cases.outbound_ports.IStoreWikiPages;
import com.zihler.wiki.application.use_cases.outbound_ports.Presenter;
import com.zihler.wiki.application.use_cases.ports.CreateWikiPagesFromBody;
import com.zihler.wiki.domain.entity.WikiPage;
import com.zihler.wiki.domain.values.*;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class CreateWikiPagesFromBodyUseCase implements CreateWikiPagesFromBody {
    private final IFindWikiPagesByReferenceTag wikiPageRetrieval;
    private final IStoreWikiPages wikiPageStorage;

    public CreateWikiPagesFromBodyUseCase(IFindWikiPagesByReferenceTag wikiPageRetrieval, IStoreWikiPages wikiPageStorage) {
        this.wikiPageRetrieval = wikiPageRetrieval;
        this.wikiPageStorage = wikiPageStorage;
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
            Optional<WikiPage> wikiPage = wikiPageRetrieval.findBy(referenceTag);
            wikiPages.add(wikiPage.orElseGet(() -> wikiPageStorage.store(WikiPage.from(referenceTag))));
        }

        return new WikiPages(wikiPages);
    }
}
