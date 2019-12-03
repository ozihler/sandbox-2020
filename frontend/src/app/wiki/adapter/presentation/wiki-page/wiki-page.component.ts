import {Component, OnInit} from '@angular/core';
import {WikiPage} from "../../../domain/wiki-page";

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

  constructor() {
  }

  ngOnInit() {
  }

  updateWikiPage(wikiPage: WikiPage) {
    this.wikiPage = wikiPage;
  }
}
