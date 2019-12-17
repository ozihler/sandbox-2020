package com.zihler.wiki.adapters.facades;

import com.zihler.wiki.adapters.presentation.rest.presenter.RestWikiPagePresenter;
import com.zihler.wiki.application.outbound_ports.gateway.FindWikiPageByTitle;
import com.zihler.wiki.application.outbound_ports.gateway.StoreWikiPage;
import com.zihler.wiki.application.use_cases.create_wiki_page.CreateWikiPageUseCase;
import com.zihler.wiki.application.use_cases.create_wiki_page.inbound_ports.CreateWikiPage;
import com.zihler.wiki.domain.values.Title;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpringCreateWikiPageUseCaseFacade {

    private CreateWikiPage createWikiPage;

    @Autowired
    public SpringCreateWikiPageUseCaseFacade(FindWikiPageByTitle findWikiPageByTitle, StoreWikiPage wikiPageStorage) {
        createWikiPage = new CreateWikiPageUseCase(findWikiPageByTitle, wikiPageStorage);
    }

    public void from(Title title, RestWikiPagePresenter presenter) {
        createWikiPage.from(title, presenter);
    }
}
