package com.zihler.wiki.adapters.presentation.rest.inputs;

import com.zihler.wiki.adapters.presentation.rest.dtos.WikiPageDto;
import com.zihler.wiki.application.outbound_ports.documents.BodyDocument;
import com.zihler.wiki.application.outbound_ports.documents.WikiPageDocument;
import com.zihler.wiki.domain.values.ReferenceTag;
import com.zihler.wiki.domain.values.Title;

public class WikiPagesInput {
    private WikiPageDto request;

    public WikiPagesInput(WikiPageDto request) {
        this.request = request;
    }

    public WikiPageDocument wikiPage() {
        return WikiPageDocument.from(ReferenceTag.from(request.getReferenceTag()), Title.from(request.getTitle()), BodyDocument.from(request.getBody()));
    }
}
