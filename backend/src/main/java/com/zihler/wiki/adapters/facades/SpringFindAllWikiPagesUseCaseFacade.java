package com.zihler.wiki.adapters.facades;

import com.zihler.wiki.adapters.presentation.rest.presenter.RestWikiPagesSearchResultPresenter;
import com.zihler.wiki.application.use_cases.find_wiki_pages.FindAllWikiPagesUseCase;
import com.zihler.wiki.application.use_cases.outbound_ports.gateway.FindAllWikiPages;
import org.springframework.stereotype.Service;

@Service
public class SpringFindAllWikiPagesUseCaseFacade {
    private com.zihler.wiki.application.use_cases.ports.FindAllWikiPages findAllWikiPages;

    public SpringFindAllWikiPagesUseCaseFacade(FindAllWikiPages findAllWikiPages) {
        this.findAllWikiPages = new FindAllWikiPagesUseCase(findAllWikiPages);
    }

    public void callWith(RestWikiPagesSearchResultPresenter presenter) {
        this.findAllWikiPages.callWith(presenter);
    }
}
