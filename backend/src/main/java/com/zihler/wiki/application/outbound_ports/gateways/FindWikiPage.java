package com.zihler.wiki.application.outbound_ports.gateways;

import com.zihler.wiki.domain.entity.WikiPage;
import com.zihler.wiki.domain.values.ReferenceTag;

import java.util.Optional;

public interface FindWikiPage {
    Optional<WikiPage> by(ReferenceTag referenceTag);
}

