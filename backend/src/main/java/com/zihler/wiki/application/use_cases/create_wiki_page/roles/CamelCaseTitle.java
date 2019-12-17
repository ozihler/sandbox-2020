package com.zihler.wiki.application.use_cases.create_wiki_page.roles;

import com.zihler.wiki.domain.values.Title;
import com.zihler.wiki.domain.values.Tokens;

public class CamelCaseTitle {
    private final Title self;

    public CamelCaseTitle(Title title) {
        self = toCamelCase(title);
    }

    private Title toCamelCase(Title title) {
        return Title.from(Tokens.from(title).toCamelCase());
    }
}
