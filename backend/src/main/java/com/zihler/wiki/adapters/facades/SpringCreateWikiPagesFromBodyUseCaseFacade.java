package com.zihler.wiki.adapters.facades;

import com.zihler.wiki.adapters.presentation.rest.presenter.RestWikiPagesPresenter;
import com.zihler.wiki.application.use_cases.CreateWikiPagesFromBodyUseCase;
import com.zihler.wiki.application.use_cases.outbound_ports.IFindWikiPagesByReferenceTag;
import com.zihler.wiki.application.use_cases.outbound_ports.IStoreWikiPages;
import com.zihler.wiki.application.use_cases.ports.CreateWikiPagesFromBody;
import com.zihler.wiki.domain.values.Body;
import org.springframework.stereotype.Service;

@Service
public class SpringCreateWikiPagesFromBodyUseCaseFacade {
    private CreateWikiPagesFromBody createWikiPagesFromBody;

    public SpringCreateWikiPagesFromBodyUseCaseFacade(IFindWikiPagesByReferenceTag wikiPageRetrieval, IStoreWikiPages wikiPageStorage) {
        this.createWikiPagesFromBody = new CreateWikiPagesFromBodyUseCase(wikiPageRetrieval, wikiPageStorage);
    }

    public void from(Body body, RestWikiPagesPresenter presenter) {
        this.createWikiPagesFromBody.from(body, presenter);
    }
}
