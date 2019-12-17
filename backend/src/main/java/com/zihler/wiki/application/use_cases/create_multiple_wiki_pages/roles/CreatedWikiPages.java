package com.zihler.wiki.application.use_cases.create_multiple_wiki_pages.roles;

import com.zihler.wiki.application.outbound_ports.gateway.FindWikiPage;
import com.zihler.wiki.application.outbound_ports.gateway.StoreWikiPage;
import com.zihler.wiki.application.use_cases.create_single_wiki_page.context.CreateSingleWikiPageContext;
import com.zihler.wiki.domain.entity.WikiPage;
import com.zihler.wiki.domain.values.*;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class CreatedWikiPages {
    private final StoreWikiPage storeWikiPage;
    private WikiPages self;

    private CreatedWikiPages(WikiPages self, StoreWikiPage storeWikiPage) {
        this.self = self;
        this.storeWikiPage = storeWikiPage;
    }

    public static CreatedWikiPages create(Body body, FindWikiPage findWikiPage, StoreWikiPage storeWikiPage) {

        BodyReferenceTags bodyReferenceTags = new BodyReferenceTags(body);


        Set<ReferenceTag> referenceTags = bodyReferenceTags.getReferenceTags();

        for (ReferenceTag referenceTag : referenceTags) {
            CreateSingleWikiPageContext.initialize(titleFrom(referenceTag), null, storeWikiPage, null);
        }

        LinkedHashSet<WikiPage> wikiPages1 = referenceTags
                .stream()
                .filter(referenceTag -> !findWikiPage.with(referenceTag))
                .map(referenceTag -> WikiPage.from(referenceTag, titleFrom(referenceTag)))
                .collect(Collectors.toCollection(LinkedHashSet::new));

        WikiPages wikiPages = new WikiPages(wikiPages1);

        return new CreatedWikiPages(wikiPages, storeWikiPage);
    }

    private static Title titleFrom(ReferenceTag referenceTag) {
        return Title.from(Tokens.withTrailingWhiteSpaceBeforeEveryUpperCaseLetter(withoutReferenceSymbol(referenceTag)).toString());
    }

    private static String withoutReferenceSymbol(ReferenceTag referenceTag) {
        return referenceTag.asString().replace(ReferenceTag.REFERENCE_SYMBOL, "");
    }

    public void storeAll() {
        self = new WikiPages(storeWikiPages());
    }

    private LinkedHashSet<WikiPage> storeWikiPages() {
        return self.getWikiPages()
                .stream()
                .map(storeWikiPage::as)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public WikiPagesDocument toDocument() {
        return WikiPagesDocument.of(self);
    }
}
