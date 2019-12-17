package com.zihler.wiki.adapters.presentation.rest.inputs;

import com.zihler.wiki.adapters.presentation.rest.dtos.WikiPageDto;
import com.zihler.wiki.domain.values.Body;

public class CreateWikiPagesFromBodyInput {
    private WikiPageDto request;

    public CreateWikiPagesFromBodyInput(WikiPageDto request) {
        this.request = request;
    }

    public Body body() {
        return Body.from(request.getBody());
    }
}
