package com.zihler.wiki.application.use_cases.update_wiki_page.roles;

import com.zihler.wiki.application.outbound_ports.documents.WikiPageDocument;
import com.zihler.wiki.application.outbound_ports.gateways.StoreWikiPage;
import com.zihler.wiki.application.outbound_ports.presenters.WikiPagePresenter;
import com.zihler.wiki.domain.entity.WikiPage;

public class UpdatedWikiPage {
    private WikiPage self;
    private StoreWikiPage storeWikiPage;
    private WikiPagePresenter output;

    public UpdatedWikiPage(WikiPage self, StoreWikiPage storeWikiPage, WikiPagePresenter output) {
        this.self = self;
        this.storeWikiPage = storeWikiPage;
        this.output = output;
    }

    public void update() {
        self = storeWikiPage.as(self);
    }

    public void present() {
        output.present(WikiPageDocument.of(self));
    }
}
