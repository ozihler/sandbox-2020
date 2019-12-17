package com.zihler.wiki.application.outbound_ports.gateway;

import com.zihler.wiki.domain.entity.WikiPage;
import com.zihler.wiki.domain.values.Title;

import java.util.Optional;

public interface FindWikiPageByTitle {
    Optional<WikiPage> having(Title title);

    default boolean existsWith(Title type) {
        return having(type).isPresent();
    }

}
