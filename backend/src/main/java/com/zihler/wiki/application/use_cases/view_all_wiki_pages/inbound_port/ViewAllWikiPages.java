package com.zihler.wiki.application.use_cases.view_all_wiki_pages.inbound_port;

import com.zihler.wiki.application.use_cases.view_all_wiki_pages.outbound_port.WikiPagesPresenter;

public interface ViewAllWikiPages {
    void callWith(WikiPagesPresenter presenter);
}
