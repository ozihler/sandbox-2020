package com.zihler.wiki.adapters.presentation.rest.viewmodels;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Set;

public class WikiPagesViewModel {
    @JsonProperty(value = "wikiPages")
    private Set<WikiPageViewModel> wikiPages;

    public WikiPagesViewModel() {
    }

    public WikiPagesViewModel(Set<WikiPageViewModel> wikiPages) {
        this.wikiPages = wikiPages;
    }

    public Set<WikiPageViewModel> getWikiPages() {
        return wikiPages;
    }
}
