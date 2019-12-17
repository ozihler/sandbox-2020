package com.zihler.wiki.application.use_cases.create_wiki_page_from_title.roles;

import com.zihler.wiki.application.use_cases.wiki_page_creation_utils.Tokens;
import com.zihler.wiki.domain.values.Title;

public class CamelCaseTitle {
    private final Title self;

    private CamelCaseTitle(Title self) {
        this.self = self;
    }

    public static CamelCaseTitle from(Title title) {
        return new CamelCaseTitle(title);
    }

    @Override
    public String toString() {
        Tokens tokens = Tokens.from(self);
        return Title.from(tokens.toCamelCase()).toString();
    }
}
