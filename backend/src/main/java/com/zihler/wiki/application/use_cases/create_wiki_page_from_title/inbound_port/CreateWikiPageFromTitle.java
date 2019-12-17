package com.zihler.wiki.application.use_cases.create_wiki_page_from_title.inbound_port;

import com.zihler.wiki.application.outbound_ports.presenters.WikiPagePresenter;
import com.zihler.wiki.domain.values.Title;

public interface CreateWikiPageFromTitle {
    void callWith(Title title, WikiPagePresenter presenter);
}
