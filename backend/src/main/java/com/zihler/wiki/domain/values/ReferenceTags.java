package com.zihler.wiki.domain.values;

import java.util.Set;

public class ReferenceTags {
    private Set<ReferenceTag> referenceTags;

    private ReferenceTags(Set<ReferenceTag> referenceTags) {
        this.referenceTags = referenceTags;
    }

    public static ReferenceTags from(Set<ReferenceTag> referenceTags) {
        return new ReferenceTags(referenceTags);
    }

    public Set<ReferenceTag> getReferenceTags() {
        return referenceTags;
    }
}
