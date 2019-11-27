package com.zihler.wiki.domain.values;

import com.zihler.wiki.domain.entity.WikiPage;

import java.util.Set;

import static java.util.stream.Collectors.toSet;

public class ReferencedDetails {

    private Set<Details> referencedDetails;

    private ReferencedDetails(Set<Details> referencedDetails) {
        this.referencedDetails = referencedDetails;
    }

    public static ReferencedDetails from(Set<ReferenceTag> referenceTags) {
        return new ReferencedDetails(toDetails(referenceTags));
    }

    private static Set<Details> toDetails(Set<ReferenceTag> referenceTags) {
        return referenceTags.stream()
                .map(DetailsBuilder::newWikiPageDetails)
                .map(DetailsBuilder::build)
                .collect(toSet());
    }

    public ReferencedWikiPages toReferencedWikiPages() {
        return ReferencedWikiPages.from(referencedDetails.stream().map(WikiPage::from).collect(toSet()));
    }
}
