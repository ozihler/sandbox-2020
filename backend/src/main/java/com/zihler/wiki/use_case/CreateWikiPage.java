package com.zihler.wiki.use_case;

import com.zihler.wiki.domain.entity.WikiPage;
import com.zihler.wiki.domain.values.Details;
import com.zihler.wiki.domain.values.ReferenceTags;
import com.zihler.wiki.use_case.inbound_port.ICreateWikiPage;
import com.zihler.wiki.use_case.outbound_ports.WikiPagePresenter;
import com.zihler.wiki.use_case.outbound_ports.WikiPageRepository;

public class CreateWikiPage implements ICreateWikiPage {
    private WikiPageRepository wikiPageRepository;

    public CreateWikiPage(WikiPageRepository wikiPageRepository) {
        this.wikiPageRepository = wikiPageRepository;
    }

    @Override
    public void withDetails(Details details, WikiPagePresenter presenter) {
        WikiPage wikiPage = new WikiPage(details);

        ReferenceTags referenceTags = details.getBody().extractReferenceTags();

        WikiPage storedWikiPage = wikiPageRepository.store(wikiPage);

        presenter.present(storedWikiPage.asDocument());
    }
}
