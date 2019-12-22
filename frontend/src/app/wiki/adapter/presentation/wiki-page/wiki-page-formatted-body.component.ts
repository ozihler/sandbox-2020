import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'app-wiki-page-formatted-body',
  template: `
    <div>
      <span [innerHTML]="formattedBody"></span>
    </div>`
})
export class WikiPageFormattedBodyComponent implements OnInit {
  @Input() formattedBody: string;

  constructor() {
  }

  ngOnInit() {
  }

}
