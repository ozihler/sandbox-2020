import {Component, OnInit} from '@angular/core';
import {WikiPage} from "../../../domain/wiki-page";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-wiki-page',
  template: `
      <app-route-button
              buttonText="Back"
              link="overview">
      </app-route-button>
      <app-wiki-page-title
              [title]="wikiPage.title"
              [referenceTag]="wikiPage.referenceTag"
              (wikiPageCreated)="updateWikiPage($event)">
      </app-wiki-page-title>
      <app-wiki-page-body
              [body]="wikiPage.body"
              [referenceTag]="wikiPage.referenceTag">
      </app-wiki-page-body>`
})
export class WikiPageComponent implements OnInit {
  private wikiPage: WikiPage = WikiPage.empty();

  constructor(private route: ActivatedRoute) {
  }

  ngOnInit() {
    this.route.queryParamMap.subscribe(params => {
      if (params.keys.length > 0) {
        this.wikiPage = WikiPage.new(params.get("referenceTag"), params.get("title"), params.get("body"));
      }
    })
  }

  updateWikiPage(wikiPage: WikiPage) {
    this.wikiPage = wikiPage;
  }
}
