package com.zihler.wiki.adapters.presentation.rest.presenters.outputs;

import com.zihler.wiki.adapters.presentation.rest.viewmodels.WikiPageViewModel;
import com.zihler.wiki.application.outbound_ports.documents.WikiPageDocument;
import com.zihler.wiki.domain.values.ReferenceTag;
import com.zihler.wiki.domain.values.ReferencedWikiPages;

import java.util.Set;

import static java.util.stream.Collectors.toSet;

public class WikiPageOutput {
    private WikiPageDocument document;

    public WikiPageOutput(WikiPageDocument document) {
        this.document = document;
    }

    public WikiPageViewModel dto() {
        return new WikiPageViewModel(
                document.referenceTag().toString(),
                document.title().toString(),
                document.body().toString(),
                toReferenceTagStrings(document.referencedWikiPages())
        );
    }

    private Set<String> toReferenceTagStrings(ReferencedWikiPages referencedWikiPages) {
        return referencedWikiPages
                .getReferencedWikiPages()
                .stream()
                .map(ReferenceTag::toString)
                .collect(toSet());
    }
}
