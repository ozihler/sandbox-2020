package com.zihler.wiki.application.outbound_ports.gateways;

import com.zihler.wiki.application.use_cases.exceptions.WikiPageNotFoundException;
import com.zihler.wiki.domain.entity.WikiPage;
import com.zihler.wiki.domain.values.ReferenceTag;

import java.util.Optional;

public interface FindWikiPage {
    default WikiPage findOrThrow(ReferenceTag referenceTag) {
        return by(referenceTag)
                .orElseThrow(WikiPageNotFoundException.thrownFor(referenceTag));
    }

    Optional<WikiPage> by(ReferenceTag referenceTag);
}

