package com.zihler.wiki.adapters.facades;

import com.zihler.wiki.adapters.presentation.rest.presenter.RestWikiPagesPresenter;
import com.zihler.wiki.application.outbound_ports.gateway.RetrieveAllWikiPages;
import com.zihler.wiki.application.use_cases.find_wiki_pages.FindAllWikiPagesUseCase;
import com.zihler.wiki.application.use_cases.find_wiki_pages.inbound_ports.FindAllWikiPages;
import org.springframework.stereotype.Service;

@Service
public class SpringFindAllWikiPagesUseCaseFacade {
    private FindAllWikiPages findAllWikiPages;

    public SpringFindAllWikiPagesUseCaseFacade(RetrieveAllWikiPages retrieveAllWikiPages) {
        this.findAllWikiPages = new FindAllWikiPagesUseCase(retrieveAllWikiPages);
    }

    public void callWith(RestWikiPagesPresenter presenter) {
        this.findAllWikiPages.callWith(presenter);
    }
}
