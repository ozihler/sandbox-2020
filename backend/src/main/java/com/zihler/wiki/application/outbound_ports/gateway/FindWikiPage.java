package com.zihler.wiki.application.outbound_ports.gateway;

import com.zihler.wiki.domain.entity.WikiPage;
import com.zihler.wiki.domain.values.ReferenceTag;

import java.util.Optional;

public interface FindWikiPage {
    Optional<WikiPage> by(ReferenceTag referenceTag);

    default boolean with(ReferenceTag referenceTag) {
        return by(referenceTag).isPresent();
    }
}

