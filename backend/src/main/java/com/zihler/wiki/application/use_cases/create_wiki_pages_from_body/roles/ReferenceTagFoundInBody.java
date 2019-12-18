package com.zihler.wiki.application.use_cases.create_wiki_pages_from_body.roles;

import com.zihler.wiki.application.outbound_ports.documents.WikiPageDocument;
import com.zihler.wiki.application.use_cases.wiki_page_creation_utils.Tokens;
import com.zihler.wiki.domain.values.Body;
import com.zihler.wiki.domain.values.ReferenceTag;
import com.zihler.wiki.domain.values.ReferencedWikiPages;
import com.zihler.wiki.domain.values.Title;

public class ReferenceTagFoundInBody {
    private ReferenceTag self;

    private ReferenceTagFoundInBody(ReferenceTag self) {
        this.self = self;
    }

    public static ReferenceTagFoundInBody from(ReferenceTag self) {
        return new ReferenceTagFoundInBody(self);
    }

    private static String withoutReferenceSymbol(ReferenceTag referenceTag) {
        return referenceTag.toString().replace(ReferenceTag.REFERENCE_SYMBOL, "");
    }

    WikiPageDocument toWikiPageDocument() {
        return WikiPageDocument.from(self, asTitle(), Body.empty(), ReferencedWikiPages.empty());
    }

    private Title asTitle() {
        String withoutReferenceSymbol = withoutReferenceSymbol(self);
        Tokens tokens = Tokens.withTrailingWhiteSpaceBeforeEveryUpperCaseLetter(withoutReferenceSymbol);
        return Title.from(tokens.toString());
    }

}
