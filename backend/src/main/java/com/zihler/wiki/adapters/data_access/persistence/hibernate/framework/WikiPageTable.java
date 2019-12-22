package com.zihler.wiki.adapters.data_access.persistence.hibernate.framework;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "WikiPageTable")
public class WikiPageTable {
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "WikiPageTable",
            joinColumns = {@JoinColumn(name = "referenceTag")},
            inverseJoinColumns = {@JoinColumn(name = "referenceTag")}
    )
    private Set<WikiPageTable> referencedWikiPages = new LinkedHashSet<>();

    @Id
    private String referenceTag;
    private String title;
    private String body;

    public WikiPageTable() {
    }

    public Set<WikiPageTable> getReferencedWikiPages() {
        return referencedWikiPages;
    }

    public void setReferencedWikiPages(Set<WikiPageTable> referencedWikiPages) {
        this.referencedWikiPages = referencedWikiPages;
    }

    public String getReferenceTag() {
        return referenceTag;
    }

    public void setReferenceTag(String referenceTag) {
        this.referenceTag = referenceTag;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
