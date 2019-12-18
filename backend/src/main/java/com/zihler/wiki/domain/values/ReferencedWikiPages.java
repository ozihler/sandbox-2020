package com.zihler.wiki.domain.values;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class ReferencedWikiPages {
    private Set<ReferenceTag> referencedWikiPages;

    private ReferencedWikiPages() {
        referencedWikiPages = new HashSet<>();
    }

    private ReferencedWikiPages(Set<ReferenceTag> referencedWikiPages) {
        this.referencedWikiPages = referencedWikiPages;
    }

    public static ReferencedWikiPages from(Set<ReferenceTag> referencedWikiPages) {
        return new ReferencedWikiPages(referencedWikiPages);
    }

    public static ReferencedWikiPages empty() {
        return new ReferencedWikiPages();
    }

    public void add(ReferenceTag referencedWikiPage) {
        referencedWikiPages.add(referencedWikiPage);
    }

    public Set<ReferenceTag> getReferencedWikiPages() {
        return referencedWikiPages;
    }

    @Override
    public String toString() {
        return "\"referencedWikiPages\":" + referencedWikiPages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReferencedWikiPages that = (ReferencedWikiPages) o;
        return Objects.equals(referencedWikiPages, that.referencedWikiPages);
    }

    @Override
    public int hashCode() {
        return Objects.hash(referencedWikiPages);
    }
}
