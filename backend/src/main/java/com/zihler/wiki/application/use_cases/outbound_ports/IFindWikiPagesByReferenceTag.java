package com.zihler.wiki.application.use_cases.outbound_ports;

import com.zihler.wiki.domain.entity.WikiPage;
import com.zihler.wiki.domain.values.ReferenceTag;

import java.util.Optional;

public interface IFindWikiPagesByReferenceTag {
    Optional<WikiPage> findBy(ReferenceTag referenceTag);
}
