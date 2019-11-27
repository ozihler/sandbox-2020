package com.zihler.wiki.use_case;

import com.zihler.wiki.domain.entity.WikiPage;
import com.zihler.wiki.domain.values.Details;
import com.zihler.wiki.use_case.inbound_port.ICreateWikiPages;
import com.zihler.wiki.use_case.outbound_ports.WikiPagePresenter;
import com.zihler.wiki.use_case.outbound_ports.WikiPageRepository;

public class CreateWikiPages implements ICreateWikiPages {
    private WikiPageRepository wikiPageRepository;

    public CreateWikiPages(WikiPageRepository wikiPageRepository) {
        this.wikiPageRepository = wikiPageRepository;
    }

    @Override
    public void from(Details details, WikiPagePresenter presenter) {
        WikiPage wikiPage = WikiPage.from(details);

        WikiPage storedWikiPage = wikiPageRepository.store(wikiPage);

        presenter.present(storedWikiPage.asDocument());
    }
}
