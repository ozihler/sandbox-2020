package com.zihler.wiki.application.use_cases.create_single_wiki_page;

import com.zihler.wiki.adapters.data_access.in_memory.InMemoryWikiPageRepository;
import com.zihler.wiki.application.outbound_ports.documents.BodyDocument;
import com.zihler.wiki.application.outbound_ports.documents.WikiPageDocument;
import com.zihler.wiki.application.use_cases.create_single_wiki_page.inbound_ports.CreateSingleWikiPage;
import com.zihler.wiki.application.use_cases.create_single_wiki_page.outbound_port.presenter.SingleWikiPagePresenter;
import com.zihler.wiki.application.use_cases.create_single_wiki_page.roles.CamelCaseTitle;
import com.zihler.wiki.application.use_cases.create_single_wiki_page.roles.ReferenceTagFromCamelCaseTitle;
import com.zihler.wiki.domain.values.Body;
import com.zihler.wiki.domain.values.Title;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CreateSingleWikiPageUseCaseTest {
    @Test
    @DisplayName("A wiki page can be created from a title")
    void test_create_wiki_page_from_title() {
        InMemoryWikiPageRepository repo = new InMemoryWikiPageRepository();

        CreateSingleWikiPage createSingleWikiPage = new CreateSingleWikiPageUseCase(repo, repo);

        Title title = Title.from("mySuper stupidTitle");
        TestPresenter presenter = new TestPresenter();

        createSingleWikiPage.from(title, presenter);

        WikiPageDocument document = presenter.getDocument();

        assertThat(document)
                .extracting(WikiPageDocument::getReferenceTag)
                .isEqualTo(ReferenceTagFromCamelCaseTitle.from(CamelCaseTitle.from(title)).get());

        assertThat(document)
                .extracting(WikiPageDocument::getTitle)
                .isEqualTo(title);

        assertThat(document)
                .extracting(WikiPageDocument::getBody)
                .isEqualTo(BodyDocument.from(Body.empty()));
    }

    private static class TestPresenter implements SingleWikiPagePresenter {
        private WikiPageDocument document;

        WikiPageDocument getDocument() {
            return document;
        }

        @Override
        public void present(WikiPageDocument document) {
            this.document = document;
        }
    }
}
