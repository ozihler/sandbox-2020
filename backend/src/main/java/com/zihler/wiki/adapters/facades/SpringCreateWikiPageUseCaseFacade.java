package com.zihler.wiki.adapters.facades;

import com.zihler.wiki.adapters.presentation.rest.presenter.RestWikiPagePresenter;
import com.zihler.wiki.application.use_cases.create_wiki_pages.CreateWikiPageUseCase;
import com.zihler.wiki.application.use_cases.outbound_ports.gateway.FindWikiPage;
import com.zihler.wiki.application.use_cases.outbound_ports.gateway.StoreWikiPage;
import com.zihler.wiki.application.use_cases.ports.CreateWikiPage;
import com.zihler.wiki.domain.values.Title;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpringCreateWikiPageUseCaseFacade {

    private CreateWikiPage createWikiPage;

    @Autowired
    public SpringCreateWikiPageUseCaseFacade(FindWikiPage<Title> wikiPageRetrieval, StoreWikiPage wikiPageStorage) {
        this.createWikiPage = new CreateWikiPageUseCase(wikiPageRetrieval, wikiPageStorage);
    }

    public void from(Title title, RestWikiPagePresenter presenter) {
        createWikiPage.from(title, presenter);
    }
}
