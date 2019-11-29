package com.zihler.wiki.application.use_cases;

import com.zihler.wiki.application.use_cases.ports.BodyDocument;
import com.zihler.wiki.application.use_cases.ports.IPresentWikiPages;
import com.zihler.wiki.application.use_cases.ports.WikiPageDocument;
import com.zihler.wiki.domain.values.ReferenceTag;
import com.zihler.wiki.domain.values.Title;

public class CreateWikiPageFromTitle {
    public static CreateWikiPageFromTitle createWikiPage() {
        return new CreateWikiPageFromTitle();
    }

    public void from(Title title, IPresentWikiPages presenter) {
        ReferenceTag referenceTag = ReferenceTag.from(title);

        WikiPageDocument wikiPage = WikiPageDocument.of(referenceTag, title, BodyDocument.from(""));

        presenter.present(wikiPage);
    }
}
