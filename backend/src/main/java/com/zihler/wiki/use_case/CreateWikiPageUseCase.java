package com.zihler.wiki.use_case;

import com.zihler.wiki.domain.entity.WikiPage;
import com.zihler.wiki.domain.values.Details;
import com.zihler.wiki.domain.values.Id;
import com.zihler.wiki.use_case.inbound_port.CreateWikiPage;
import com.zihler.wiki.use_case.outbound_ports.WikiPagePresenter;

public class CreateWikiPageUseCase implements CreateWikiPage {
    @Override
    public void withDetails(Details details, WikiPagePresenter presenter) {

        WikiPage wikiPage = new WikiPage(Id.of(1L), details);

        presenter.present(wikiPage.asDocument());
    }
}
