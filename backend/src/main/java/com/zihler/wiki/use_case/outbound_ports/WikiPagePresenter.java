package com.zihler.wiki.use_case.outbound_ports;

import com.zihler.wiki.domain.values.WikiPageDocument;

public interface WikiPagePresenter {
    void present(WikiPageDocument document);
}
