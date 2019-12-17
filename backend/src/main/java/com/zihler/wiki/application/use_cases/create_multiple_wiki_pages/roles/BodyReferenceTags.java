package com.zihler.wiki.application.use_cases.create_multiple_wiki_pages.roles;

import com.zihler.wiki.application.outbound_ports.gateway.FindWikiPage;
import com.zihler.wiki.application.outbound_ports.gateway.StoreWikiPage;
import com.zihler.wiki.application.use_cases.create_single_wiki_page.outbound_port.presenter.SingleWikiPagePresenter;
import com.zihler.wiki.domain.values.Body;
import com.zihler.wiki.domain.values.ReferenceTag;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.zihler.wiki.domain.values.Patterns.NON_CHARACTER_TOKEN_REGEX;
import static com.zihler.wiki.domain.values.Patterns.REFERENCE_TAG_MATCHING_REGEX;

public class BodyReferenceTags {
    private Set<BodyReferenceTag> self;

    private BodyReferenceTags(Set<BodyReferenceTag> self) {
        this.self = self;
    }

    public static BodyReferenceTags from(Body body, FindWikiPage findWikiPage, StoreWikiPage storeWikiPage, SingleWikiPagePresenter presenter) {
        Set<BodyReferenceTag> bodyReferenceTags =
                extractReferenceTagsFrom(body)
                        .stream()
                        .map(referenceTag -> BodyReferenceTag.from(referenceTag, findWikiPage, storeWikiPage, presenter))
                        .collect(Collectors.toSet());

        return new BodyReferenceTags(bodyReferenceTags);
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


    public void createAndStoreWikiPages() {
        for (BodyReferenceTag referenceTag : self) {
            referenceTag.createAndStoreWikiPage();
        }
    }
}
