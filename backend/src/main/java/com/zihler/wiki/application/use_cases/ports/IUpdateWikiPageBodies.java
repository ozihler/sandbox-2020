package com.zihler.wiki.application.use_cases.ports;

import com.zihler.wiki.adapters.presentation.presenter.rest.UpdateWikiPageBodyPresenter;

public interface IUpdateWikiPageBodies {
    void of(WikiPageDocument wikiPage, UpdateWikiPageBodyPresenter presenter);
}
