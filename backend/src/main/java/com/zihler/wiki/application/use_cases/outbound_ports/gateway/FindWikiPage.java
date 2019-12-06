package com.zihler.wiki.application.use_cases.outbound_ports.gateway;

import com.zihler.wiki.domain.entity.WikiPage;

import java.util.Optional;

public interface FindWikiPage<T> {
    Optional<WikiPage> by(T type);


    default boolean having(T type) {
        return by(type).isPresent();
    }
}
