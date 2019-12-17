package com.zihler.wiki.application.use_cases.create_wiki_page_from_title.roles;

import com.zihler.wiki.domain.values.ReferenceTag;
import com.zihler.wiki.domain.values.Title;

import static java.lang.String.format;

public class TitleBasedReferenceTag {
    private final ReferenceTag self;

    private TitleBasedReferenceTag(Title title) {
        self = ReferenceTag.from(format("%s%s", ReferenceTag.REFERENCE_SYMBOL, CamelCaseTitle.from(title)));
    }

    public static TitleBasedReferenceTag from(Title title) {
        return new TitleBasedReferenceTag(title);
    }

    public ReferenceTag get() {
        return self;
    }
}
