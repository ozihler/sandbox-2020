package com.zihler.wiki.adapters.presentation.controllers.rest.dtos;

import com.zihler.wiki.application.use_cases.ports.WikiPageDocument;

public class WikiPageResponse {
    private final String referenceTag;
    private final String title;
    private final String body;

    public WikiPageResponse(String referenceTag, String title, String body) {
        this.referenceTag = referenceTag;
        this.title = title;
        this.body = body;
    }

    public static WikiPageResponse from(WikiPageDocument document) {
        return new WikiPageResponse(
                document.getReferenceTag().toString(),
                document.getTitle().toString(),
                document.getBody().toString()
        );
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
}
