package com.zihler.wiki.adapters.presentation.rest.controllers.inputs;

import com.zihler.wiki.domain.values.Title;

public class TitleInput {
    private String request;

    public TitleInput(String request) {
        this.request = request;
    }

    public Title title() {
        return Title.from(request);
    }
}
