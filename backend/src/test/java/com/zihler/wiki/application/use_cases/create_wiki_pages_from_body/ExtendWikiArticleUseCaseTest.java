package com.zihler.wiki.application.use_cases.create_wiki_pages_from_body;

import com.zihler.wiki.adapters.data_access.persistence.in_memory.InMemoryWikiPageRepository;
import com.zihler.wiki.application.outbound_ports.documents.WikiPageDocument;
import com.zihler.wiki.application.outbound_ports.gateways.FindWikiPage;
import com.zihler.wiki.application.outbound_ports.gateways.StoreWikiPage;
import com.zihler.wiki.application.outbound_ports.presenters.WikiPagePresenter;
import com.zihler.wiki.application.use_cases.create_wiki_pages_from_body.inbound_port.ExtendWikiArticle;
import com.zihler.wiki.domain.entity.WikiPage;
import com.zihler.wiki.domain.values.Body;
import com.zihler.wiki.domain.values.ReferenceTag;
import com.zihler.wiki.domain.values.ReferencedWikiPages;
import com.zihler.wiki.domain.values.Title;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

class ExtendWikiArticleUseCaseTest {

    @Test
    void happyCase() {
        var output = new TestWikiPagesPresenter();


        String tag1 = "#ThisIsARefTag";
        String tag2 = "#WithCertainPersuasion";

        InMemoryWikiPageRepository repo = new InMemoryWikiPageRepository();
        StoreWikiPage storeWikiPage = repo;
        FindWikiPage findWikiPage = repo;

        var wikiPageToUpdate = WikiPage.from(
                ReferenceTag.from("#HelloWorld"),
                Title.from("Hello World"),
                Body.from("New Body"),
                ReferencedWikiPages.empty());

        storeWikiPage.as(wikiPageToUpdate);

        ExtendWikiArticle createWikiPagesFromBody = new ExtendWikiArticleUseCase(findWikiPage, storeWikiPage);

        Body bodyToParse = Body.from(String.format("Hello world %s and %s.", tag1, tag2));

        WikiPageDocument updatedWikiArticle = WikiPageDocument.from(wikiPageToUpdate.getReferenceTag(), wikiPageToUpdate.getTitle(), bodyToParse);

        createWikiPagesFromBody.with(updatedWikiArticle, output);

        ReferenceTag referenceTag1 = ReferenceTag.from(tag1);
        ReferenceTag referenceTag2 = ReferenceTag.from(tag2);

        WikiPageDocument expectedUpdatedWikiPage = WikiPageDocument.from(
                wikiPageToUpdate.getReferenceTag(),
                wikiPageToUpdate.getTitle(),
                updatedWikiArticle.body(),
                ReferencedWikiPages.from(Set.of(referenceTag1, referenceTag2))
        );

        assertThat(output.actualWikiPage).isEqualToComparingFieldByField(expectedUpdatedWikiPage);

        assertThat(repo.get().getWikiPages().size()).isEqualTo(3);

        Set<ReferenceTag> expectedReferenceTags = Set.of(updatedWikiArticle.referenceTag(), referenceTag1, referenceTag2);

        Set<ReferenceTag> actualStoredReferenceTags = repo.get().getWikiPages().stream().map(WikiPage::getReferenceTag).collect(Collectors.toSet());
        assertThat(actualStoredReferenceTags).containsAll(expectedReferenceTags);

    }

    private class TestWikiPagesPresenter implements WikiPagePresenter {
        private WikiPageDocument actualWikiPage;

        @Override
        public void present(WikiPageDocument actualWikiPage) {
            this.actualWikiPage = actualWikiPage;
        }
    }
}