package com.zihler.wiki.application.use_cases.find_wiki_pages.inbound_port;

import com.zihler.wiki.application.use_cases.find_wiki_pages.outbound_port.WikiPagesSearchResultPresenter;

public interface FindAllWikiPages {
    void andSendThemTo(WikiPagesSearchResultPresenter presenter);
}
