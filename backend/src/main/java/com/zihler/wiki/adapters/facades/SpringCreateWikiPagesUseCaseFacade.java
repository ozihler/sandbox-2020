package com.zihler.wiki.adapters.facades;

import com.zihler.wiki.adapters.presentation.rest.presenter.RestWikiPagesPresenter;
import com.zihler.wiki.application.use_cases.create_wiki_pages.CreateWikiPagesUseCase;
import com.zihler.wiki.application.use_cases.outbound_ports.gateway.FindWikiPage;
import com.zihler.wiki.application.use_cases.outbound_ports.gateway.StoreWikiPage;
import com.zihler.wiki.application.use_cases.ports.CreateWikiPages;
import com.zihler.wiki.domain.values.Body;
import com.zihler.wiki.domain.values.ReferenceTag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpringCreateWikiPagesUseCaseFacade {
    private CreateWikiPages createWikiPages;

    @Autowired
    public SpringCreateWikiPagesUseCaseFacade(FindWikiPage<ReferenceTag> findWikiPages, StoreWikiPage storeWikiPage) {
        this.createWikiPages = new CreateWikiPagesUseCase(findWikiPages, storeWikiPage);
    }

    public void from(Body body, RestWikiPagesPresenter presenter) {
        this.createWikiPages.from(body, presenter);
    }
}
