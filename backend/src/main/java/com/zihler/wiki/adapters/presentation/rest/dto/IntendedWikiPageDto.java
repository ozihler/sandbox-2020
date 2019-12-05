package com.zihler.wiki.adapters.presentation.rest.dto;

public class IntendedWikiPageDto {
    private String title;
    private String body;
    private String referenceTag;

    public String getBody() {
        return body;
    }

    public String getReferenceTag() {
        return referenceTag;
    }

    public String getTitle() {
        return title;
    }
}
