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

    public static CreatedWikiPages from(WikiPagesDocument intendedWikiPages, WikiPagePresenter presenter, FindWikiPage findWikiPage, StoreWikiPage storeWikiPage) {
        return new CreatedWikiPages(intendedWikiPages.getWikiPages()
                .stream()
                .map(wikiPage -> CreatedWikiPage.from(wikiPage, storeWikiPage, presenter, findWikiPage))
                .collect(Collectors.toList()));
    }

    public void present() {
        self.forEach(CreatedWikiPage::present);
    }

    public void store() {
        self.forEach(CreatedWikiPage::store);
    }
}
