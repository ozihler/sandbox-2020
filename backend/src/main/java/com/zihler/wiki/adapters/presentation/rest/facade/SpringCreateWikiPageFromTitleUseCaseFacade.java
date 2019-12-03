package com.zihler.wiki.adapters.presentation.rest.facade;

import com.zihler.wiki.application.use_cases.CreateWikiPageFromTitleUseCase;
import com.zihler.wiki.application.use_cases.outbound_ports.IFindWikiPages;
import com.zihler.wiki.application.use_cases.outbound_ports.IStoreWikiPages;
import com.zihler.wiki.application.use_cases.ports.CreateWikiPageFromTitle;
import com.zihler.wiki.application.use_cases.ports.WikiPagePresenter;
import com.zihler.wiki.domain.values.Title;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpringCreateWikiPageFromTitleUseCaseFacade {

    private CreateWikiPageFromTitle createWikiPageFromTitle;

    @Autowired
    public SpringCreateWikiPageFromTitleUseCaseFacade(IFindWikiPages wikiPageRetrieval, IStoreWikiPages wikiPageStorage) {
        this.createWikiPageFromTitle = new CreateWikiPageFromTitleUseCase(wikiPageRetrieval, wikiPageStorage);
    }

    public void from(Title title, WikiPagePresenter presenter) {
        createWikiPageFromTitle.from(title, presenter);
    }
}
