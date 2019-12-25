package com.zihler.wiki.adapters.presentation.rest.presenters.outputs;

import com.zihler.wiki.adapters.presentation.rest.viewmodels.WikiPageViewModel;
import com.zihler.wiki.adapters.presentation.rest.viewmodels.WikiPagesViewModel;
import com.zihler.wiki.application.outbound_ports.documents.WikiPageDocument;
import com.zihler.wiki.application.outbound_ports.documents.WikiPagesDocument;

import java.util.Set;

import static java.util.stream.Collectors.toSet;

public class WikiPagesOutput {

    private WikiPagesDocument wikiPagesDocument;

    public WikiPagesOutput(WikiPagesDocument wikiPagesDocument) {
        this.wikiPagesDocument = wikiPagesDocument;
    }

    private Set<WikiPageViewModel> toResponses(Set<WikiPageDocument> wikiPages) {
        return wikiPages.stream().map(WikiPageOutput::new).map(WikiPageOutput::dto).collect(toSet());
    }

    public WikiPagesViewModel dto() {
        return new WikiPagesViewModel(toResponses(wikiPagesDocument.getWikiPages()));
    }
}
