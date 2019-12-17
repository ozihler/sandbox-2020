package com.zihler.wiki.application.use_cases.create_wiki_page.roles;

import com.zihler.wiki.domain.values.Title;
import com.zihler.wiki.domain.values.Tokens;

public class CamelCaseTitle {
    private final Title self;

    private CamelCaseTitle(Title title) {
        self = Title.from(Tokens.from(title).toCamelCase());
    }

    public static CamelCaseTitle from(Title title) {
        return new CamelCaseTitle(title);
    }

    public String asString() {
        return self.asString();
    }
}
