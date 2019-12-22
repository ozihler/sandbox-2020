package com.zihler.wiki.application.use_cases.fetch_wiki_page.inbound_port;

import com.zihler.wiki.application.outbound_ports.presenters.WikiPagePresenter;
import com.zihler.wiki.domain.values.ReferenceTag;

public interface FetchWikiPage {
    void byReferenceTag(ReferenceTag referenceTag, WikiPagePresenter output);
}
