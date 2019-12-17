package com.zihler.wiki.application.use_cases.create_single_wiki_page.inbound_ports;

import com.zihler.wiki.application.use_cases.create_single_wiki_page.outbound_port.presenter.SingleWikiPagePresenter;
import com.zihler.wiki.domain.values.Title;

public interface CreateSingleWikiPage {
    void from(Title title, SingleWikiPagePresenter presenter);
}
