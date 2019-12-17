package com.zihler.wiki.application.use_cases.create_wiki_pages_from_body;

import com.zihler.wiki.adapters.data_access.in_memory.InMemoryWikiPageRepository;
import com.zihler.wiki.application.outbound_ports.documents.WikiPageDocument;
import com.zihler.wiki.application.outbound_ports.presenter.WikiPagePresenter;
import com.zihler.wiki.application.use_cases.create_wiki_pages_from_body.inbound_ports.CreateWikiPagesFromBody;
import com.zihler.wiki.domain.values.Body;
import com.zihler.wiki.domain.values.WikiPagesDocument;
import org.junit.jupiter.api.Test;

class CreateWikiPagesFromBodyUseCaseTest {

    @Test
    void happyCase() {
        var repo = new InMemoryWikiPageRepository();
        var presenter = new TestMultipleWikiPagesPresenter();

        CreateWikiPagesFromBody createWikiPagesFromBody = new CreateWikiPagesFromBodyFromBodyUseCase(repo, repo);

        createWikiPagesFromBody.from(Body.from("Hello world #ThisIsARefTag and #WithCertainPersuasion."), presenter);

        System.out.println(presenter.wikiPages);
    }

    private class TestMultipleWikiPagesPresenter implements WikiPagePresenter {
        private WikiPagesDocument wikiPages = new WikiPagesDocument();

        @Override
        public void present(WikiPageDocument wikiPageDocument) {
            wikiPages.add(wikiPageDocument);
        }
    }
}
