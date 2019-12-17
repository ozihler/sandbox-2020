package com.zihler.wiki.application.use_cases.create_wiki_pages.roles;

import com.zihler.wiki.domain.values.Body;
import com.zihler.wiki.domain.values.ReferenceTag;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.zihler.wiki.domain.values.Patterns.NON_CHARACTER_TOKEN_REGEX;
import static com.zihler.wiki.domain.values.Patterns.REFERENCE_TAG_MATCHING_REGEX;

class BodyReferenceTags {
    private Body self;

    BodyReferenceTags(Body self) {
        this.self = self;
    }

    private static Set<ReferenceTag> extractReferenceTagsFrom(Body body) {
        return toWordTokens(body)
                .filter(BodyReferenceTags::isReferenceTag)
                .map(ReferenceTag::from)
                .collect(Collectors.toSet());
    }

    private static Stream<String> toWordTokens(Body body) {
        return Arrays.stream(body.asString().split(NON_CHARACTER_TOKEN_REGEX.toString()));
    }

    private static boolean isReferenceTag(String word) {
        return REFERENCE_TAG_MATCHING_REGEX.toPattern().matcher(word).find();
    }

    Set<ReferenceTag> getReferenceTags() {
        return extractReferenceTagsFrom(self);
    }
}
