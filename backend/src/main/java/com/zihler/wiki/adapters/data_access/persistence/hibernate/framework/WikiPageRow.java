package com.zihler.wiki.adapters.data_access.persistence.hibernate.framework;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "wiki_page_row")
public class WikiPageRow implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "reference_tag", nullable = false)
    private String referenceTag;

    @Column(name = "title")
    private String title;

    @Column(name = "body")
    private String body;

    @JoinTable(
            name = "referenced_wiki_pages",
            joinColumns = {
                    @JoinColumn(name = "referencer", referencedColumnName = "reference_tag")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "referenced", referencedColumnName = "reference_tag")
            }
    )
    @ManyToMany
    private Set<WikiPageRow> referencedWikiPages = new HashSet<>();

    public WikiPageRow() {
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

    public Set<WikiPageRow> getReferencedWikiPages() {
        return referencedWikiPages;
    }

    public void setReferencedWikiPages(Set<WikiPageRow> referencedWikiPages) {
        this.referencedWikiPages = referencedWikiPages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WikiPageRow that = (WikiPageRow) o;
        return Objects.equals(referencedWikiPages, that.referencedWikiPages) &&
                Objects.equals(referenceTag, that.referenceTag) &&
                Objects.equals(title, that.title) &&
                Objects.equals(body, that.body);
    }

    @Override
    public int hashCode() {
        return Objects.hash(referencedWikiPages, referenceTag, title, body);
    }
}
