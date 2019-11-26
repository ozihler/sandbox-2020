package com.zihler.wiki.domain.values.builder;

import com.zihler.wiki.domain.exceptions.IllegalReferenceTagException;
import com.zihler.wiki.domain.values.Body;
import com.zihler.wiki.domain.values.Details;
import com.zihler.wiki.domain.values.ReferenceTag;
import com.zihler.wiki.domain.values.Title;

import static java.lang.String.*;

public class DetailsBuilder {
    private ReferenceTag referenceTag;
    private Title title;
    private Body body;

    private DetailsBuilder(ReferenceTag referenceTag) {
        this.referenceTag = referenceTag;
    }

    public static DetailsBuilder newWikiPageDetails(ReferenceTag referenceTag) {
        return new DetailsBuilder(referenceTag);
    }

    public DetailsBuilder withTitle(Title title) {
        this.title = title;
        return this;
    }

    public DetailsBuilder withBody(Body body) {
        this.body = body;
        return this;
    }

    public Details build() {
        if (referenceTag == null || !referenceTag.isValid()) {
            throw new IllegalReferenceTagException(format("Reference tag invalid: %s", referenceTag));
        }

        if (title == null) {
            title = Title.empty();
        }

        if (body == null) {
            body = Body.empty();
        }

        return new Details(referenceTag, title, body);
    }
}
