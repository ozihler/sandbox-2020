package com.zihler.wiki.application.use_cases.create_single_wiki_page.inbound_ports;

import com.zihler.wiki.application.outbound_ports.presenter.WikiPagePresenter;
import com.zihler.wiki.domain.values.Title;

public interface CreateSingleWikiPage {
    void from(Title title, WikiPagePresenter presenter);
}
