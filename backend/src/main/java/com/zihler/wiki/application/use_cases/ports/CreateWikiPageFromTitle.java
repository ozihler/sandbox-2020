package com.zihler.wiki.application.use_cases.ports;

import com.zihler.wiki.domain.values.Title;

public interface CreateWikiPageFromTitle {
    void from(Title title, WikiPagePresenter presenter);
}
