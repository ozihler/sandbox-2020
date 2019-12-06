package com.zihler.wiki.adapters.facades;

import com.zihler.wiki.adapters.presentation.rest.presenter.RestWikiPagesSearchResultPresenter;
import com.zihler.wiki.application.use_cases.find_wiki_pages.FindAllWikiPagesUseCase;
import com.zihler.wiki.application.use_cases.outbound_ports.gateway.RetrieveAllWikiPages;
import org.springframework.stereotype.Service;

@Service
public class SpringFindAllWikiPagesUseCaseFacade {
    private com.zihler.wiki.application.use_cases.ports.FindAllWikiPages findAllWikiPages;

    public SpringFindAllWikiPagesUseCaseFacade(RetrieveAllWikiPages retrieveAllWikiPages) {
        this.findAllWikiPages = new FindAllWikiPagesUseCase(retrieveAllWikiPages);
    }

    public void callWith(RestWikiPagesSearchResultPresenter presenter) {
        this.findAllWikiPages.callWith(presenter);
    }
}
