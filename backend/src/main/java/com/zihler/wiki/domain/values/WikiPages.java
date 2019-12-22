package com.zihler.wiki.domain.values;

import com.zihler.wiki.domain.entity.WikiPage;

import java.util.HashSet;
import java.util.Set;

public class WikiPages {
    private Set<WikiPage> wikiPages;

    private WikiPages(Set<WikiPage> wikiPages) {
        this.wikiPages = new HashSet<>();
        this.wikiPages = wikiPages;
    }

    public static WikiPages from(Set<WikiPage> wikiPages) {
        return new WikiPages(wikiPages);
    }

    public Set<WikiPage> getWikiPages() {
        return wikiPages;
    }

}
