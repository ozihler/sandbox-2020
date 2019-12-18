package com.zihler.wiki.application.use_cases.exceptions;

import com.zihler.wiki.domain.values.ReferenceTag;

import java.util.function.Supplier;

public class WikiPageNotFoundException extends RuntimeException {
    private WikiPageNotFoundException(String message) {
        super(message);
    }

    public static Supplier<WikiPageNotFoundException> thrownFor(ReferenceTag referenceTag) {
        return () -> new WikiPageNotFoundException("Could not find Wiki Page with reference tag " + referenceTag);
    }
}
