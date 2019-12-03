package com.zihler.wiki.adapters.presentation.rest.facade;

import com.zihler.wiki.adapters.presentation.rest.presenter.RestWikiPagesSearchResultPresenter;
import com.zihler.wiki.application.use_cases.FindWikiPagesUseCase;
import com.zihler.wiki.application.use_cases.outbound_ports.IFindAllWikiPages;
import com.zihler.wiki.application.use_cases.ports.FindWikiPages;
import org.springframework.stereotype.Service;

@Service
public class SpringFindWikiPagesUseCaseFacade {
    private FindWikiPages findWikiPages;

    public SpringFindWikiPagesUseCaseFacade(IFindAllWikiPages findAllWikiPages) {
        this.findWikiPages = new FindWikiPagesUseCase(findAllWikiPages);
    }

    public void all(RestWikiPagesSearchResultPresenter presenter) {
        this.findWikiPages.all(presenter);
    }
}
