package com.zihler.wiki.application.use_cases;

import com.zihler.wiki.adapters.presentation.presenter.rest.UpdateWikiPageBodyPresenter;
import com.zihler.wiki.application.use_cases.ports.IUpdateWikiPageBodies;
import com.zihler.wiki.application.use_cases.ports.WikiPageDocument;

public class UpdateWikiPageBody implements IUpdateWikiPageBodies {

    private UpdateWikiPageBody() {
    }

    public static IUpdateWikiPageBodies updateWikiPageBody() {
        return new UpdateWikiPageBody();
    }

    @Override
    public void of(WikiPageDocument wikiPage, UpdateWikiPageBodyPresenter presenter) {

    }
}
