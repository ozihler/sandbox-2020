package com.zihler.wiki.adapters.presentation.rest.presenters;

import com.zihler.wiki.adapters.presentation.rest.presenters.outputs.WikiPageOutput;
import com.zihler.wiki.adapters.presentation.rest.viewmodels.WikiPageViewModel;
import com.zihler.wiki.application.outbound_ports.documents.WikiPageDocument;
import com.zihler.wiki.application.outbound_ports.presenters.WikiPagePresenter;
import org.springframework.http.ResponseEntity;

public class RestWikiPagePresenter implements WikiPagePresenter {
    private ResponseEntity<WikiPageViewModel> response;

    @Override
    public void present(WikiPageDocument document) {
        WikiPageOutput wikiPageOutput = new WikiPageOutput(document);
        WikiPageViewModel viewModel = wikiPageOutput.dto();
        response = ResponseEntity.ok(viewModel);
    }

    public ResponseEntity<WikiPageViewModel> getResponseEntity() {
        return response;
    }
}
