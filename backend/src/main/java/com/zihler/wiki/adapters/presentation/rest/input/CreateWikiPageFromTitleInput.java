package com.zihler.wiki.adapters.presentation.rest.input;

import com.zihler.wiki.adapters.presentation.rest.dto.IntendedWikiPageDto;
import com.zihler.wiki.domain.values.Title;

public class CreateWikiPageFromTitleInput {
    private IntendedWikiPageDto request;

    public CreateWikiPageFromTitleInput(IntendedWikiPageDto request) {
        this.request = request;
    }

    public Title title() {
        return Title.from(request.getTitle());
    }
}
