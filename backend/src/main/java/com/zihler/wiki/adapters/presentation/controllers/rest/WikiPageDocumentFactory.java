package com.zihler.wiki.adapters.presentation.controllers.rest;

import com.zihler.wiki.application.use_cases.ports.BodyDocument;
import com.zihler.wiki.application.use_cases.ports.WikiPageDocument;
import com.zihler.wiki.domain.values.ReferenceTag;

class WikiPageDocumentFactory {
    static WikiPageDocument from(UpdateWikiPageBodyRequest request) {
        ReferenceTag referenceTag = ReferenceTag.from(request.getReferenceTag());
        BodyDocument body = BodyDocument.from(request.getBody());
        return WikiPageDocument.of(referenceTag, body);
    }
}
