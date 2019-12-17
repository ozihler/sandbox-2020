package com.zihler.wiki.application.use_cases.create_multiple_wiki_pages.inbound_ports;

import com.zihler.wiki.application.use_cases.create_multiple_wiki_pages.outbound_port.presenter.MultipleWikiPagesPresenter;
import com.zihler.wiki.domain.values.Body;

public interface CreateWikiPages {
    void from(Body body, MultipleWikiPagesPresenter presenter);
}
