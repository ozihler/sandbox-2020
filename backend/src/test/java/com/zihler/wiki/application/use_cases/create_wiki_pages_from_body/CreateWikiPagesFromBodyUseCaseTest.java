package com.zihler.wiki.application.use_cases.create_wiki_pages_from_body;

import com.zihler.wiki.application.outbound_ports.documents.WikiPageDocument;
import com.zihler.wiki.application.outbound_ports.documents.WikiPagesDocument;
import com.zihler.wiki.application.outbound_ports.gateways.FindWikiPage;
import com.zihler.wiki.application.outbound_ports.gateways.StoreWikiPage;
import com.zihler.wiki.application.outbound_ports.presenters.WikiPagePresenter;
import com.zihler.wiki.application.use_cases.create_wiki_pages_from_body.inbound_port.CreateWikiPagesFromBody;
import com.zihler.wiki.domain.values.Body;
import com.zihler.wiki.domain.values.ReferenceTag;
import com.zihler.wiki.domain.values.Title;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashSet;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class CreateWikiPagesFromBodyUseCaseTest {

    @Test
    void happyCase() {
        var presenter = new TestMultipleWikiPagesPresenter();

        String tag1 = "#ThisIsARefTag";
        String tag2 = "#WithCertainPersuasion";

        LinkedHashSet<WikiPageDocument> wikiPages = new LinkedHashSet<>();
        wikiPages.add(WikiPageDocument.from(Title.from("This Is A Ref Tag"), ReferenceTag.from(tag1)));
        wikiPages.add(WikiPageDocument.from(Title.from("With Certain Persuasion"), ReferenceTag.from(tag2)));
        WikiPagesDocument expectedWikiPages = new WikiPagesDocument(wikiPages);

        StoreWikiPage storeWikiPage = (wikiPage) -> wikiPage;
        FindWikiPage findWikiPage = (tag) -> Optional.empty();

        CreateWikiPagesFromBody createWikiPagesFromBody = new CreateWikiPagesFromBodyUseCase(findWikiPage, storeWikiPage);

        Body bodyToParse = Body.from(String.format("Hello world %s and %s.", tag1, tag2));

        createWikiPagesFromBody.callWith(bodyToParse, presenter);

        WikiPagesDocument actualWikiPages = presenter.wikiPages;

        assertThat(actualWikiPages)
                .isEqualToComparingFieldByField(expectedWikiPages);

    }

    private class TestMultipleWikiPagesPresenter implements WikiPagePresenter {
        private WikiPagesDocument wikiPages = new WikiPagesDocument();

        @Override
        public void present(WikiPageDocument wikiPageDocument) {
            wikiPages.add(wikiPageDocument);
        }
    }
}
