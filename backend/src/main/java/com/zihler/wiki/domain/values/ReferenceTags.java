package com.zihler.wiki.domain.values;

import java.util.Set;
import java.util.stream.Collectors;

import static com.zihler.wiki.domain.values.Patterns.REFERENCE_TAG_MATCHING_REGEX;

public class ReferenceTags {

    private final Set<ReferenceTag> referenceTags;

    private ReferenceTags(Set<ReferenceTag> referenceTags) {
        this.referenceTags = referenceTags;
    }

    public static ReferenceTags from(Body body) {
        return new ReferenceTags(extractReferenceTagsFrom(body));
    }

    // TODO: 12/6/2019 Move to use case
    private static Set<ReferenceTag> extractReferenceTagsFrom(Body body) {
        return body.toWordTokens()
                .filter(ReferenceTags::isReferenceTag)
                .map(ReferenceTag::from)
                .collect(Collectors.toSet());
    }

    // TODO: 12/6/2019 Maybe move to use case?
    private static boolean isReferenceTag(String word) {
        return REFERENCE_TAG_MATCHING_REGEX.toPattern().matcher(word).find();
    }

    public Set<ReferenceTag> getReferenceTags() {
        return referenceTags;
    }
}
