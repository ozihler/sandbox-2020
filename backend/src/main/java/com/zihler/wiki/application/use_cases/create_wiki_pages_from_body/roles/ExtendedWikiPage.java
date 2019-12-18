package com.zihler.wiki.application.use_cases.create_wiki_pages_from_body.roles;

import com.zihler.wiki.application.outbound_ports.documents.WikiPageDocument;
import com.zihler.wiki.application.outbound_ports.gateways.FindWikiPage;
import com.zihler.wiki.application.outbound_ports.presenters.WikiPagePresenter;
import com.zihler.wiki.domain.entity.WikiPage;
import com.zihler.wiki.domain.values.Body;
import com.zihler.wiki.domain.values.ReferenceTag;

public class ExtendedWikiPage {
    private WikiPage self;
    private WikiPagePresenter output;

    private ExtendedWikiPage(WikiPage self, WikiPagePresenter output) {
        this.self = self;
        this.output = output;
    }

    public static ExtendedWikiPage from(ReferenceTag referenceTag, FindWikiPage findWikiPage, WikiPagePresenter output) {
        WikiPage self = findWikiPage.findOrThrow(referenceTag);

        return new ExtendedWikiPage(self, output);
    }

    public void updateWith(Body updatedBody) {
        self.setBody(updatedBody);
    }

    void add(ReferenceTag referenceTag) {
        self.add(referenceTag);
    }

    public void present() {
        output.present(WikiPageDocument.of(self));
    }
}
