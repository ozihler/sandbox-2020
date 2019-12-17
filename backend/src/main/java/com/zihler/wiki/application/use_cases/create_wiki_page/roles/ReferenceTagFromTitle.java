package com.zihler.wiki.application.use_cases.create_wiki_page.roles;

import com.zihler.wiki.domain.values.ReferenceTag;

import static java.lang.String.format;

public class ReferenceTagFromTitle {
    private static final String REFERENCE_SYMBOL = "#";
    private final ReferenceTag self;

    public ReferenceTagFromTitle(CamelCaseTitle title) {
        self = ReferenceTag.from(format("%s%s", REFERENCE_SYMBOL, title.toString()));
    }

    public ReferenceTag get() {
        return self;
    }
}
