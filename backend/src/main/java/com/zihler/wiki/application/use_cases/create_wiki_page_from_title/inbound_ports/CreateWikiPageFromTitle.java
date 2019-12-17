package com.zihler.wiki.application.use_cases.create_wiki_page_from_title.inbound_ports;

import com.zihler.wiki.application.outbound_ports.presenter.WikiPagePresenter;
import com.zihler.wiki.domain.values.Title;

public interface CreateWikiPageFromTitle {
    void callWith(Title title, WikiPagePresenter presenter);
}
