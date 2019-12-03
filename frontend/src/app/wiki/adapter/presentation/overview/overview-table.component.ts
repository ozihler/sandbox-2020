import {Component, Input, OnInit} from '@angular/core';
import {WikiPage} from "../../../domain/wiki-page";

@Component({
  selector: 'app-overview-table',
  template: `
      <table class="table">
          <tr>
              <td>Wiki Pages</td>
          </tr>
          <tr *ngFor="let wikiPage of wikiPages">
              <td><a [routerLink]="['/wiki-page']"
                     [queryParams]="wikiPage.asDto()">{{wikiPage.title.title}}</a>
              </td>
          </tr>
      </table>`
})
export class OverviewTableComponent implements OnInit {

  @Input() wikiPages: WikiPage[];

  constructor() {
  }

  ngOnInit() {
  }

}

