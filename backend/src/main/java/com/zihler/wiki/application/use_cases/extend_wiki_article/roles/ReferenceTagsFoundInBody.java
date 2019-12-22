package com.zihler.wiki.application.use_cases.extend_wiki_article.roles;

import com.zihler.wiki.application.outbound_ports.documents.WikiPagesDocument;
import com.zihler.wiki.domain.values.Body;
import com.zihler.wiki.domain.values.ReferenceTag;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Stream;

import static com.zihler.wiki.domain.values.Patterns.NON_CHARACTER_TOKEN_REGEX;
import static com.zihler.wiki.domain.values.Patterns.REFERENCE_TAG_MATCHING_REGEX;
import static java.util.stream.Collectors.toSet;

public class ReferenceTagsFoundInBody {
    private Set<ReferenceTagFoundInBody> referenceTagsFoundInBody;

    private ReferenceTagsFoundInBody(Set<ReferenceTagFoundInBody> referenceTagsFoundInBody) {
        this.referenceTagsFoundInBody = referenceTagsFoundInBody;
    }

    public static ReferenceTagsFoundInBody from(Body body) {
        return new ReferenceTagsFoundInBody(extractReferenceTagsFrom(body)
                .stream()
                .map(ReferenceTagFoundInBody::from)
                .collect(toSet()));
    }

    private static Set<ReferenceTag> extractReferenceTagsFrom(Body body) {
        return toWordTokens(body)
                .filter(ReferenceTagsFoundInBody::isReferenceTag)
                .map(ReferenceTag::from)
                .collect(toSet());
    }

    private static Stream<String> toWordTokens(Body body) {
        return Arrays.stream(body.toString().split(NON_CHARACTER_TOKEN_REGEX.toString()));
    }

    private static boolean isReferenceTag(String word) {
        return REFERENCE_TAG_MATCHING_REGEX.toPattern().matcher(word).find();
    }

    public WikiPagesDocument toWikiPagesDocument() {
        return new WikiPagesDocument(referenceTagsFoundInBody
                .stream()
                .map(ReferenceTagFoundInBody::toWikiPageDocument)
                .collect(toSet()));
    }
}
