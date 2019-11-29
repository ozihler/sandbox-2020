package com.zihler.wiki.domain.values;

import java.util.Arrays;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ReferenceTags {
    public static final Pattern REFERENCE_TAG_MATCHING_PATTERN = Pattern.compile("(.*(?<!.)#[A-Z0-9]+[a-z0-9]*)+");

    private final Set<ReferenceTag> referenceTags;

    private ReferenceTags(Set<ReferenceTag> referenceTags) {
        this.referenceTags = referenceTags;
    }

    static ReferenceTags from(Body body) {
        return new ReferenceTags(extractReferenceTagsFrom(body));
    }

    private static Set<ReferenceTag> extractReferenceTagsFrom(Body body) {
        return body.toWordTokens()
                .filter(ReferenceTags::isReferenceTag)
                .map(ReferenceTag::from)
                .collect(Collectors.toSet());
    }

    private static boolean isReferenceTag(String word) {
        return REFERENCE_TAG_MATCHING_PATTERN.matcher(word).find();
    }

    public boolean isEmpty() {
        return referenceTags.isEmpty();
    }

    public int count() {
        return referenceTags.size();
    }

    public boolean containsAll(ReferenceTag referenceTag, ReferenceTag... referenceTags) {
        return this.referenceTags.contains(referenceTag) || this.referenceTags.containsAll(Arrays.asList(referenceTags));
    }

    public ReferencedDetails toReferencedDetails() {
        return ReferencedDetails.from(referenceTags);
    }

}
