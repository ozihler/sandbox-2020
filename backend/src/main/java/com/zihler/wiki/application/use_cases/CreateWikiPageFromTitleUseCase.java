package com.zihler.wiki.application.use_cases;

import com.zihler.wiki.application.use_cases.outbound_ports.IFindWikiPagesByTitle;
import com.zihler.wiki.application.use_cases.outbound_ports.IStoreWikiPages;
import com.zihler.wiki.application.use_cases.outbound_ports.Presenter;
import com.zihler.wiki.application.use_cases.outbound_ports.WikiPageDocument;
import com.zihler.wiki.application.use_cases.ports.CreateWikiPageFromTitle;
import com.zihler.wiki.domain.entity.WikiPage;
import com.zihler.wiki.domain.values.Title;

import java.util.Optional;

public class CreateWikiPageFromTitleUseCase implements CreateWikiPageFromTitle {

    private final IFindWikiPagesByTitle wikiPageRetrieval;
    private final IStoreWikiPages wikiPageStorage;

    public CreateWikiPageFromTitleUseCase(IFindWikiPagesByTitle wikiPageRetrieval, IStoreWikiPages wikiPageStorage) {
        this.wikiPageRetrieval = wikiPageRetrieval;
        this.wikiPageStorage = wikiPageStorage;
    }

    @Override
    public void from(Title title, Presenter<WikiPageDocument> presenter) {
        WikiPage wikiPage = findOrCreateFrom(title);

        WikiPageDocument wikiPageDocument = WikiPageDocument.of(wikiPage);

        presenter.present(wikiPageDocument);
    }

    private WikiPage findOrCreateFrom(Title title) {
        Optional<WikiPage> wikiPage = wikiPageRetrieval.findFor(title);

        if (wikiPage.isEmpty()) {
            return wikiPageStorage.store(WikiPage.from(title));
        } else {
            return wikiPage.get();
        }
    }

}
