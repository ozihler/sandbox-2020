import {Component, OnInit} from '@angular/core';
import {FetchWikiPages} from "../../../application.use_case/fetch-wiki-pages.port";
import {WikiPage} from "../../../domain/wiki-page";
import {FetchWikiPagesGateway} from "../../gateway/fetch-wiki-pages/fetch-wiki-pages.service";

@Component({
  selector: 'app-wiki',
  providers: [{provide: FetchWikiPages, useClass: FetchWikiPagesGateway}],
  template: `
      <app-route-button
              buttonText="New"
              link="wiki-page">
      </app-route-button>
      <app-overview-table [wikiPages]="wikiPages">
      </app-overview-table>`
})
export class WikiComponent implements OnInit {
  private wikiPages: WikiPage[];

  constructor(private fetchWikiPages: FetchWikiPages) {
  }

  ngOnInit() {
    this.fetchWikiPages.all()
      .subscribe(response => {
        this.wikiPages = response;
      })
  }
}
