package com.zihler.wiki.application.use_cases;

import com.zihler.wiki.application.use_cases.outbound_ports.IFindWikiPages;
import com.zihler.wiki.application.use_cases.outbound_ports.IStoreWikiPages;
import com.zihler.wiki.application.use_cases.ports.CreateWikiPageFromTitle;
import com.zihler.wiki.application.use_cases.ports.WikiPageDocument;
import com.zihler.wiki.application.use_cases.ports.WikiPagePresenter;
import com.zihler.wiki.domain.entity.WikiPage;
import com.zihler.wiki.domain.values.Title;

import java.util.Optional;

public class CreateWikiPageFromTitleUseCase implements CreateWikiPageFromTitle {

    private final IFindWikiPages wikiPageRetrieval;
    private final IStoreWikiPages wikiPageStorage;

    public CreateWikiPageFromTitleUseCase(IFindWikiPages wikiPageRetrieval, IStoreWikiPages wikiPageStorage) {
        this.wikiPageRetrieval = wikiPageRetrieval;
        this.wikiPageStorage = wikiPageStorage;
    }

    @Override
    public void from(Title title, WikiPagePresenter presenter) {
        WikiPage wikiPage = findOrCreateFrom(title);

        WikiPageDocument wikiPageDocument = WikiPageDocument.of(wikiPage);

        presenter.present(wikiPageDocument);
    }

    private WikiPage findOrCreateFrom(Title title) {
        Optional<WikiPage> wikiPage = wikiPageRetrieval.findBy(title);

        if (wikiPage.isEmpty()) {
            return wikiPageStorage.store(WikiPage.from(title));
        } else {
            return wikiPage.get();
        }
    }

}
