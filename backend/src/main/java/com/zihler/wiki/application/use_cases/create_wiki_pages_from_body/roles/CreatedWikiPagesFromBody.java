package com.zihler.wiki.application.use_cases.create_wiki_pages_from_body.roles;

import com.zihler.wiki.application.habits.create_wiki_page.inbound_ports.CreateWikiPage;
import com.zihler.wiki.application.outbound_ports.documents.WikiPageDocument;
import com.zihler.wiki.application.outbound_ports.presenter.WikiPagePresenter;
import com.zihler.wiki.application.use_cases.create_wiki_page_from_title.roles.CreatedWikiPageFromTitle;
import com.zihler.wiki.domain.values.WikiPagesDocument;

import java.util.ArrayList;
import java.util.List;

public class CreatedWikiPagesFromBody {
    private List<CreatedWikiPageFromTitle> self;

    private CreatedWikiPagesFromBody(List<CreatedWikiPageFromTitle> self) {
        this.self = self;
    }

    public static CreatedWikiPagesFromBody from(WikiPagesDocument intendedWikiPages, WikiPagePresenter presenter, CreateWikiPage createWikiPage) {

        List<CreatedWikiPageFromTitle> createdWikiPageFromTitles = new ArrayList<>();
        for (WikiPageDocument wikiPage : intendedWikiPages.getWikiPages()) {
            createdWikiPageFromTitles.add(CreatedWikiPageFromTitle.from(wikiPage, createWikiPage, presenter));
        }
        return new CreatedWikiPagesFromBody(createdWikiPageFromTitles);
    }

    public void storeAndPresent() {
        for (CreatedWikiPageFromTitle intendedWikiPage : self) {
            intendedWikiPage.storeAndPresent();
        }
    }
}
