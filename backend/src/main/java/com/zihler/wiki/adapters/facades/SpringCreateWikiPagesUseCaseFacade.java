package com.zihler.wiki.adapters.facades;

import com.zihler.wiki.adapters.presentation.rest.presenter.RestWikiPagesPresenter;
import com.zihler.wiki.application.outbound_ports.gateway.FindWikiPageByReferenceTag;
import com.zihler.wiki.application.outbound_ports.gateway.StoreWikiPage;
import com.zihler.wiki.application.use_cases.create_wiki_pages.CreateWikiPagesUseCase;
import com.zihler.wiki.application.use_cases.create_wiki_pages.inbound_ports.CreateWikiPages;
import com.zihler.wiki.domain.values.Body;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpringCreateWikiPagesUseCaseFacade {
    private CreateWikiPages createWikiPages;

    @Autowired
    public SpringCreateWikiPagesUseCaseFacade(FindWikiPageByReferenceTag findWikiPages, StoreWikiPage storeWikiPage) {
        createWikiPages = new CreateWikiPagesUseCase(findWikiPages, storeWikiPage);
    }

    public void from(Body body, RestWikiPagesPresenter presenter) {
        createWikiPages.from(body, presenter);
    }
}
