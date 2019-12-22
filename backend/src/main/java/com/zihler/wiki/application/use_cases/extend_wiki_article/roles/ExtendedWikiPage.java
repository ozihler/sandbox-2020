package com.zihler.wiki.application.use_cases.extend_wiki_article.roles;

import com.zihler.wiki.application.outbound_ports.documents.WikiPageDocument;
import com.zihler.wiki.application.outbound_ports.gateways.FindWikiPage;
import com.zihler.wiki.application.outbound_ports.gateways.StoreWikiPage;
import com.zihler.wiki.application.outbound_ports.presenters.WikiPagePresenter;
import com.zihler.wiki.domain.entity.WikiPage;
import com.zihler.wiki.domain.values.Body;
import com.zihler.wiki.domain.values.ReferenceTag;

public class ExtendedWikiPage {
    private WikiPage self;
    private StoreWikiPage storeWikiPage;
    private WikiPagePresenter output;

    private ExtendedWikiPage(WikiPage self, StoreWikiPage storeWikiPage, WikiPagePresenter output) {
        this.self = self;
        this.storeWikiPage = storeWikiPage;
        this.output = output;
    }

    public static ExtendedWikiPage from(ReferenceTag referenceTag, StoreWikiPage storeWikiPage, FindWikiPage findWikiPage, WikiPagePresenter output) {
        WikiPage self = findWikiPage.findOrThrow(referenceTag);

        return new ExtendedWikiPage(self, storeWikiPage, output);
    }

    public void updateWith(Body updatedBody) {
        self.setBody(updatedBody);
        self = storeWikiPage.as(self);
    }

    void add(ReferenceTag referenceTag) {
        self.add(referenceTag);
    }

    public void present() {
        output.present(WikiPageDocument.of(self));
    }

}
