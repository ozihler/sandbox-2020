import {Component, OnInit} from '@angular/core';
import {WikiPage} from '../../../domain/wiki-page';
import {ActivatedRoute} from '@angular/router';
import {FetchWikiPage} from '../../gateway/fetch-wiki-pages/fetch-wiki-page.service';
import {ReferenceTag} from '../../../domain/reference-tag';

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
      [wikiPage]="wikiPage"
      (wikiPageUpdated)="updateWikiPage($event)">
    </app-wiki-page-body>
    <app-wiki-page-formatted-body
      [formattedBody]="formatBody(wikiPage)">
    </app-wiki-page-formatted-body>`
})
export class WikiPageComponent implements OnInit {
  private wikiPage: WikiPage = WikiPage.empty();

  constructor(private route: ActivatedRoute, private fetchWikiPage: FetchWikiPage) {
  }

  ngOnInit() {
    this.route.queryParamMap.subscribe(params => {
      if (params.keys.length > 0) {
        this.fetchWikiPage.withReferenceTag(ReferenceTag.from(params.get('referenceTag')))
          .subscribe(wikiPage => {
            this.wikiPage = wikiPage;
          });
      }
    });
  }

  updateWikiPage(wikiPage: WikiPage) {
    this.wikiPage = wikiPage;
  }

  private formatBody(wikiPage: WikiPage) {
    let formattedBody = '';
    if (this.wikiPage.body.body) {
      formattedBody = this.wikiPage.body.body;
      wikiPage.referencedWikiPages.referencedWikiPages.forEach(
        refTag => {
          formattedBody = formattedBody.replace(
            refTag.referenceTag,
            `<a [routerLink]="['/wiki-page']"
            [queryParams]="wikiPage.asDto()">{{wikiPage.title.title}}</a>`
          );
        }
      );
    }
    return formattedBody;
  }
}
