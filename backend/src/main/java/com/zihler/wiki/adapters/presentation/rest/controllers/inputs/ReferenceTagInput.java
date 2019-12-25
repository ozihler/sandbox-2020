package com.zihler.wiki.adapters.presentation.rest.controllers.inputs;

import com.zihler.wiki.domain.values.ReferenceTag;

public class ReferenceTagInput {
    private String referenceTag;

    public ReferenceTagInput(String referenceTag) {
        this.referenceTag = referenceTag;
    }

    public ReferenceTag referenceTag() {
        return ReferenceTag.from(referenceTag);
    }
}
