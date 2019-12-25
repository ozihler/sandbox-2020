package com.zihler.wiki.adapters.presentation.rest.viewmodels;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Set;

public class WikiPageViewModel implements Comparable<WikiPageViewModel> {
    @JsonProperty(value = "referenceTag")
    private String referenceTag;
    @JsonProperty(value = "title")
    private String title;
    @JsonProperty(value = "body")
    private String body;
    @JsonProperty(value = "referencedWikiPages")
    private Set<String> referencedWikiPages;

    public WikiPageViewModel() {
    }

    public WikiPageViewModel(String referenceTag, String title, String body, Set<String> referencedWikiPages) {
        this.referenceTag = referenceTag;
        this.title = title;
        this.body = body;
        this.referencedWikiPages = referencedWikiPages;
    }

    public String getReferenceTag() {
        return referenceTag;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public Set<String> getReferencedWikiPages() {
        return referencedWikiPages;
    }

    @Override
    public int compareTo(WikiPageViewModel wikiPageViewModel) {
        return referenceTag.compareTo(wikiPageViewModel.referenceTag);
    }
}
