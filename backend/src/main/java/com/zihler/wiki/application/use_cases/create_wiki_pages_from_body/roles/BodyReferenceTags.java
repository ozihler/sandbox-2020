package com.zihler.wiki.application.use_cases.create_wiki_pages_from_body.roles;

import com.zihler.wiki.domain.values.Body;
import com.zihler.wiki.domain.values.ReferenceTag;
import com.zihler.wiki.domain.values.WikiPagesDocument;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Stream;

import static com.zihler.wiki.domain.values.Patterns.NON_CHARACTER_TOKEN_REGEX;
import static com.zihler.wiki.domain.values.Patterns.REFERENCE_TAG_MATCHING_REGEX;
import static java.util.stream.Collectors.toSet;

public class BodyReferenceTags {
    private Set<BodyReferenceTag> bodyReferenceTags;

    private BodyReferenceTags(Set<BodyReferenceTag> bodyReferenceTags) {
        this.bodyReferenceTags = bodyReferenceTags;
    }

    public static BodyReferenceTags from(Body body) {
        return new BodyReferenceTags(extractReferenceTagsFrom(body)
                .stream()
                .map(BodyReferenceTag::from)
                .collect(toSet()));
    }

    private static Set<ReferenceTag> extractReferenceTagsFrom(Body body) {
        return toWordTokens(body)
                .filter(BodyReferenceTags::isReferenceTag)
                .map(ReferenceTag::from)
                .collect(toSet());
    }

    private static Stream<String> toWordTokens(Body body) {
        return Arrays.stream(body.asString().split(NON_CHARACTER_TOKEN_REGEX.toString()));
    }

    private static boolean isReferenceTag(String word) {
        return REFERENCE_TAG_MATCHING_REGEX.toPattern().matcher(word).find();
    }

    public WikiPagesDocument toWikiPagesDocument() {
        return new WikiPagesDocument(bodyReferenceTags
                .stream()
                .map(BodyReferenceTag::toWikiPageDocument)
                .collect(toSet()));
    }
}
