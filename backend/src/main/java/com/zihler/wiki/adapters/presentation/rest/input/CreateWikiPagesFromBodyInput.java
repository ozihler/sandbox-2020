package com.zihler.wiki.adapters.presentation.rest.input;

import com.zihler.wiki.adapters.presentation.rest.dto.IntendedWikiPageDto;
import com.zihler.wiki.domain.values.Body;

public class CreateWikiPagesFromBodyInput {
    private IntendedWikiPageDto request;

    public CreateWikiPagesFromBodyInput(IntendedWikiPageDto request) {
        this.request = request;
    }

    public Body body() {
        return Body.from(this.request.getBody());
    }
}
