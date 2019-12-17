package com.zihler.wiki.application.use_cases.create_multiple_wiki_pages.roles;

import com.zihler.wiki.application.outbound_ports.gateway.FindWikiPage;
import com.zihler.wiki.application.outbound_ports.gateway.StoreWikiPage;
import com.zihler.wiki.application.use_cases.create_single_wiki_page.context.CreateSingleWikiPageUseCaseContext;
import com.zihler.wiki.application.use_cases.create_single_wiki_page.outbound_port.presenter.SingleWikiPagePresenter;
import com.zihler.wiki.domain.values.ReferenceTag;
import com.zihler.wiki.domain.values.Title;
import com.zihler.wiki.domain.values.Tokens;

class BodyReferenceTag {
    private ReferenceTag self;
    private FindWikiPage findWikiPage;
    private StoreWikiPage storeWikiPage;
    private SingleWikiPagePresenter presenter;

    private BodyReferenceTag(ReferenceTag self, FindWikiPage findWikiPage, StoreWikiPage storeWikiPage, SingleWikiPagePresenter presenter) {
        this.self = self;
        this.findWikiPage = findWikiPage;
        this.storeWikiPage = storeWikiPage;
        this.presenter = presenter;
    }


    public static BodyReferenceTag from(ReferenceTag self, FindWikiPage findWikiPage, StoreWikiPage storeWikiPage, SingleWikiPagePresenter presenter) {
        return new BodyReferenceTag(self, findWikiPage, storeWikiPage, presenter);
    }

    private static ReferenceTag withoutReferenceSymbol(ReferenceTag referenceTag) {
        return ReferenceTag.from(referenceTag.asString().replace(ReferenceTag.REFERENCE_SYMBOL, ""));
    }

    private Title asTitle() {
        ReferenceTag withoutReferenceSymbol = withoutReferenceSymbol(self);
        Tokens tokens = Tokens.withTrailingWhiteSpaceBeforeEveryUpperCaseLetter(withoutReferenceSymbol.asString());
        return Title.from(tokens.toString());
    }

    void createAndStoreWikiPage() {
        CreateSingleWikiPageUseCaseContext
                .initialize(asTitle(), findWikiPage, storeWikiPage, presenter)
                .enactUseCase();
    }
}
