package com.zihler.wiki.application.use_cases;

import com.zihler.wiki.application.use_cases.ports.WikiPageDocument;
import com.zihler.wiki.application.use_cases.ports.WikiPagePresenter;
import com.zihler.wiki.domain.entity.WikiPage;
import com.zihler.wiki.domain.values.Title;

public class CreateWikiPageFromTitle {
    public static CreateWikiPageFromTitle createWikiPage() {
        return new CreateWikiPageFromTitle();
    }

    public void from(Title title, WikiPagePresenter presenter) {
        WikiPage wikiPage = WikiPage.from(title);

        WikiPageDocument wikiPageDocument = WikiPageDocument.of(wikiPage);

        presenter.present(wikiPageDocument);
    }

}
