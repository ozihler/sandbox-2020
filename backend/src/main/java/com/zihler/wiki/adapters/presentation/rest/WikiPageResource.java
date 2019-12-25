package com.zihler.wiki.adapters.presentation.rest;

import com.zihler.wiki.adapters.presentation.rest.controllers.CreateWikiPageController;
import com.zihler.wiki.adapters.presentation.rest.controllers.ExtendWikiArticleController;
import com.zihler.wiki.adapters.presentation.rest.controllers.FetchWikiPageController;
import com.zihler.wiki.adapters.presentation.rest.controllers.ViewAllWikiPagesController;
import com.zihler.wiki.adapters.presentation.rest.presenters.RestWikiPagePresenter;
import com.zihler.wiki.adapters.presentation.rest.presenters.RestWikiPagesPresenter;
import com.zihler.wiki.adapters.presentation.rest.viewmodels.WikiPageViewModel;
import com.zihler.wiki.adapters.presentation.rest.viewmodels.WikiPagesViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "wiki/pages", produces = "application/json")
public class WikiPageResource {
    private CreateWikiPageController createWikiPageController;
    private ViewAllWikiPagesController viewAllWikiPagesController;
    private ExtendWikiArticleController extendWikiArticleController;
    private FetchWikiPageController fetchWikiPageController;

    @Autowired
    public WikiPageResource(CreateWikiPageController createWikiPageController,
                            ViewAllWikiPagesController viewAllWikiPagesController,
                            ExtendWikiArticleController extendWikiArticleController,
                            FetchWikiPageController fetchWikiPageController) {
        this.createWikiPageController = createWikiPageController;
        this.viewAllWikiPagesController = viewAllWikiPagesController;
        this.extendWikiArticleController = extendWikiArticleController;
        this.fetchWikiPageController = fetchWikiPageController;
    }

    @PostMapping(path = "/title")
    public ResponseEntity<WikiPageViewModel> createWikiPageFromTitle(@RequestBody WikiPageViewModel jsonRequest) {
        var output = new RestWikiPagePresenter();

        createWikiPageController.createWikiPage(jsonRequest.getTitle(), output);

        return output.getResponseEntity();
    }

    @PostMapping(path = "/body")
    public ResponseEntity<WikiPageViewModel> extendWikiArticle(@RequestBody WikiPageViewModel request) {
        var output = new RestWikiPagePresenter();

        extendWikiArticleController.extendWith(request, output);

        return output.getResponseEntity();
    }

    @GetMapping
    public ResponseEntity<WikiPagesViewModel> fetchAllWikiPages() {
        var output = new RestWikiPagesPresenter();

        viewAllWikiPagesController.viewAllWikiPages(output);

        return output.getResponseEntity();
    }

    @GetMapping("/{referenceTag}")
    ResponseEntity<WikiPageViewModel> fetchWikiPageByReferenceTag(@PathVariable("referenceTag") String referenceTag) {
        var output = new RestWikiPagePresenter();

        fetchWikiPageController.fetchBy(referenceTag, output);

        return output.getResponseEntity();
    }

}
