package com.zihler.wiki.application.use_cases.find_wiki_pages.inbound_ports;

import com.zihler.wiki.application.use_cases.create_multiple_wiki_pages.outbound_port.presenter.MultipleWikiPagesPresenter;

public interface FindAllWikiPages {
    void callWith(MultipleWikiPagesPresenter presenter);
}
