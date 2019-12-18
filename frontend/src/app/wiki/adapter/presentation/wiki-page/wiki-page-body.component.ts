import {Component, Input, OnChanges, SimpleChanges} from '@angular/core';

import {FormControl} from '@angular/forms';
import {debounceTime, distinctUntilChanged} from 'rxjs/operators';
import {Body} from '../../../domain/body';
import {UpdateWikiPageBody} from '../../../application.use_case/update-wiki-page-body.port';
import {UpdateWikiPageBodyGateway} from '../../gateway/update-wiki-page-body/update-wiki-page-body.service';
import {WikiPage} from '../../../domain/wiki-page';

@Component({
  selector: 'app-wiki-page-body',
  providers: [{provide: UpdateWikiPageBody, useClass: UpdateWikiPageBodyGateway}],
  template: `
    <div>
          <textarea id="wiki-page-body"
                    class="form-control"
                    [formControl]="bodyInput"
                    *ngIf="!wikiPage.referenceTag.isEmpty()"></textarea>

      <span [innerHTML]="wikiPage.body.body"></span>
    </div>`
})
export class WikiPageBodyComponent implements OnChanges {

  @Input() wikiPage: WikiPage;
  bodyInput: FormControl;

  constructor(private updateWikiPageBody: UpdateWikiPageBody) {
    this.wikiPage = WikiPage.empty();
  }

  ngOnChanges(changes: SimpleChanges): void {
    this.bodyInput = new FormControl(this.wikiPage.body.body);

    this.bodyInput.valueChanges.pipe(
      debounceTime(2000),
      distinctUntilChanged()
    ).subscribe(res => {
      this.wikiPage.body = Body.from(res);

      this.updateWikiPageBody.with(this.wikiPage)
        .subscribe(wikiPage => {
          this.wikiPage = wikiPage;
        });
    });
  }
}

