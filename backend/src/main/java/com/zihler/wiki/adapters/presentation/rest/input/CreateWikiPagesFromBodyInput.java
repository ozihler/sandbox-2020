package com.zihler.wiki.adapters.presentation.rest.input;

import com.zihler.wiki.adapters.presentation.rest.dto.WikiPageDto;
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
