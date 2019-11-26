package com.zihler.wiki.use_case;

import com.zihler.wiki.domain.values.Body;
import com.zihler.wiki.domain.values.Details;
import com.zihler.wiki.domain.values.ReferenceTag;
import com.zihler.wiki.domain.values.Title;
import com.zihler.wiki.presentation.presenter.RestWikiPagePresenter;
import com.zihler.wiki.use_case.inbound_port.CreateWikiPage;
import org.junit.jupiter.api.Test;

import static com.zihler.wiki.domain.values.builder.DetailsBuilder.newWikiPageDetails;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CreateWikiPageTest {

    @Test
    void create_wiki_page__creates_new_wiki_page() {
        Details details = newWikiPageDetails(ReferenceTag.from("#MyTestWikiPage"))
                .withTitle(Title.from("My Test Title"))
                .withBody(Body.from("# my test body"))
                .build();

        CreateWikiPage createWikiPage = new CreateWikiPageUseCase();

        createWikiPage.withDetails(details, (wikiPageDocument) -> {
            assertTrue(wikiPageDocument.id().longValue() > 0L);
            assertEquals(details.asDocument().toString(), wikiPageDocument.toString());
        });
    }
}
