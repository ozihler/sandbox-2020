package com.zihler.wiki.application.use_cases.create_single_wiki_page.roles;

import com.zihler.wiki.domain.values.ReferenceTag;

import static java.lang.String.format;

public class TitleReferenceTag {
    private final ReferenceTag self;

    private TitleReferenceTag(CamelCaseTitle title) {
        self = ReferenceTag.from(format("%s%s", ReferenceTag.REFERENCE_SYMBOL, title.asString()));
    }

    public static TitleReferenceTag from(CamelCaseTitle title) {
        return new TitleReferenceTag(title);
    }

    public ReferenceTag get() {
        return self;
    }
}
