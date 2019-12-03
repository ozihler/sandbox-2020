import {Component, Input, OnInit} from '@angular/core';
import {WikiPage} from "../../../../domain/wiki-page";

@Component({
  selector: 'app-overview-table',
  template: `
      <table class="table">
          <tr>
              <td>Title</td>
              <td>Reference Tag</td>
          </tr>
          <tr *ngFor="let wikiPage of wikiPages">
              <td>{{wikiPage.title.title}}</td>
              <td>{{wikiPage.referenceTag.referenceTag}}</td>
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
