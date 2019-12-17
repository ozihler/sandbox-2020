package com.zihler.wiki.adapters.presentation.rest.input;

import com.zihler.wiki.adapters.presentation.rest.dto.WikiPageDto;
import com.zihler.wiki.domain.values.Title;

public class CreateWikiPageFromTitleInput {
    private WikiPageDto request;

    public CreateWikiPageFromTitleInput(WikiPageDto request) {
        this.request = request;
    }

    public Title title() {
        return Title.from(request.getTitle());
    }
}
