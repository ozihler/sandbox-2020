package com.zihler.wiki.use_case;

import com.zihler.wiki.domain.values.*;
import com.zihler.wiki.use_case.outbound_ports.WikiPagePresenter;
import org.junit.jupiter.api.Test;

import static com.zihler.wiki.domain.values.builder.DetailsBuilder.newWikiPageDetails;
import static com.zihler.wiki.use_case.CreateWikiPage.createWikiPage;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ICreateWikiPageTest {

    @Test
    void create_wiki_page__creates_new_wiki_page() {
        Details details = newWikiPageDetails(ReferenceTag.from("#MyTestWikiPage"))
                .withTitle(Title.from("My Test Title"))
                .withBody(Body.from("# my test body"))
                .build();

        TestWikiPagePresenter testPresenter = new TestWikiPagePresenter();

        createWikiPage().withDetails(details, testPresenter);

        assertTrue(testPresenter.document.id().longValue() > 0L);
        assertEquals(details.asDocument().toString(), testPresenter.document.toString());
    }

    static class TestWikiPagePresenter implements WikiPagePresenter {
        WikiPageDocument document;

        @Override
        public void present(WikiPageDocument document) {
            this.document = document;
        }
    }
}
