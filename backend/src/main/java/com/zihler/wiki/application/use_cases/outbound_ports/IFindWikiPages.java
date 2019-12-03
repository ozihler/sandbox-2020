package com.zihler.wiki.application.use_cases.outbound_ports;

import com.zihler.wiki.domain.entity.WikiPage;
import com.zihler.wiki.domain.values.Title;

import java.util.Optional;

public interface IFindWikiPages {
    Optional<WikiPage> findBy(Title title);
}
