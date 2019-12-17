package com.zihler.wiki.application.use_cases.create_multiple_wiki_pages.inbound_ports;

import com.zihler.wiki.application.outbound_ports.presenter.WikiPagePresenter;
import com.zihler.wiki.domain.values.Body;

public interface CreateMultipleWikiPages {
    void from(Body body, WikiPagePresenter presenter);
}
