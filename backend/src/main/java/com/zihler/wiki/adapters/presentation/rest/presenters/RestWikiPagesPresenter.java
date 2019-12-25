package com.zihler.wiki.adapters.presentation.rest.presenters;

import com.zihler.wiki.adapters.presentation.rest.presenters.outputs.WikiPagesOutput;
import com.zihler.wiki.adapters.presentation.rest.viewmodels.WikiPagesViewModel;
import com.zihler.wiki.application.outbound_ports.documents.WikiPagesDocument;
import com.zihler.wiki.application.use_cases.view_all_wiki_pages.outbound_port.WikiPagesPresenter;
import org.springframework.http.ResponseEntity;

public class RestWikiPagesPresenter implements WikiPagesPresenter {
    private ResponseEntity<WikiPagesViewModel> responseEntity;

    @Override
    public void present(WikiPagesDocument wikiPagesDocument) {
        var wikiPagesOutput = new WikiPagesOutput(wikiPagesDocument);
        responseEntity = ResponseEntity.ok(wikiPagesOutput.dto());
    }

    public ResponseEntity<WikiPagesViewModel> getResponseEntity() {
        return responseEntity;
    }
}
