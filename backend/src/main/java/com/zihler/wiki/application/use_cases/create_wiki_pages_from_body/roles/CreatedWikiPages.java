package com.zihler.wiki.application.use_cases.create_wiki_pages_from_body.roles;

import com.zihler.wiki.application.outbound_ports.documents.WikiPagesDocument;
import com.zihler.wiki.application.outbound_ports.gateways.FindWikiPage;
import com.zihler.wiki.application.outbound_ports.gateways.StoreWikiPage;
import com.zihler.wiki.application.outbound_ports.presenters.WikiPagePresenter;
import com.zihler.wiki.application.use_cases.create_wiki_page_from_title.roles.CreatedWikiPage;

import java.util.List;
import java.util.stream.Collectors;

public class CreatedWikiPages {
    private List<CreatedWikiPage> self;

    private CreatedWikiPages(List<CreatedWikiPage> self) {
        this.self = self;
    }

    public static CreatedWikiPages from(WikiPagesDocument intendedWikiPages, FindWikiPage findWikiPage, StoreWikiPage storeWikiPage) {
        List<CreatedWikiPage> self = toCreatedWikiPages(intendedWikiPages, findWikiPage, storeWikiPage, null);
        return new CreatedWikiPages(self);
    }

    private static List<CreatedWikiPage> toCreatedWikiPages(WikiPagesDocument intendedWikiPages, FindWikiPage findWikiPage, StoreWikiPage storeWikiPage, WikiPagePresenter output) {
        return intendedWikiPages.getWikiPages()
                .stream()
                .map(wikiPage -> CreatedWikiPage.from(wikiPage, findWikiPage, storeWikiPage, output))
                .collect(Collectors.toList());
    }

    public CreatedWikiPages storeAll() {
        self.forEach(CreatedWikiPage::store);
        return this;
    }

    public void linkWith(ExistingWikiPage existingWikiPage) {
        self.stream()
                .map(CreatedWikiPage::getReferenceTag)
                .forEach(existingWikiPage::add);
    }
}
