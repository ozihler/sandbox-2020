package com.zihler.wiki.application.outbound_ports.gateway;

import com.zihler.wiki.domain.entity.WikiPage;
import com.zihler.wiki.domain.values.ReferenceTag;

import java.util.Optional;

public interface FindWikiPageByReferenceTag {
    Optional<WikiPage> having(ReferenceTag referenceTag);

    default boolean existsWith(ReferenceTag referenceTag) {
        return having(referenceTag).isPresent();
    }
}

