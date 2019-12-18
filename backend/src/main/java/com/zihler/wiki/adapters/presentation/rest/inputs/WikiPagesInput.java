package com.zihler.wiki.adapters.presentation.rest.inputs;

import com.zihler.wiki.adapters.presentation.rest.dtos.WikiPageDto;
import com.zihler.wiki.application.outbound_ports.documents.WikiPageDocument;
import com.zihler.wiki.domain.values.Body;
import com.zihler.wiki.domain.values.ReferenceTag;
import com.zihler.wiki.domain.values.ReferencedWikiPages;
import com.zihler.wiki.domain.values.Title;

import java.util.Set;

import static java.util.stream.Collectors.toSet;

public class WikiPagesInput {
    private WikiPageDto request;

    public WikiPagesInput(WikiPageDto request) {
        this.request = request;
    }

    public WikiPageDocument wikiPage() {
        return WikiPageDocument.from(
                ReferenceTag.from(request.getReferenceTag()),
                Title.from(request.getTitle()),
                Body.from(request.getBody()),
                ReferencedWikiPages.from(referencedWikiPages()));
    }

    private Set<ReferenceTag> referencedWikiPages() {
        return request.getReferencedWikiPages().stream().map(ReferenceTag::from).collect(toSet());
    }
}
