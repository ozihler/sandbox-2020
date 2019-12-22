package com.zihler.wiki.application.use_cases.fetch_wiki_page.roles;

import com.zihler.wiki.application.outbound_ports.documents.WikiPageDocument;
import com.zihler.wiki.application.outbound_ports.presenters.WikiPagePresenter;
import com.zihler.wiki.domain.entity.WikiPage;

public class FoundWikiPage {
    private final WikiPage self;
    private final WikiPagePresenter output;

    public FoundWikiPage(WikiPage self, WikiPagePresenter output) {
        this.self = self;
        this.output = output;
    }

    public void present() {
        output.present(WikiPageDocument.from(self));
    }
}
