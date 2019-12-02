import {Component, Input, OnChanges, SimpleChanges} from '@angular/core';
import {Body} from "../../domain/values/body";
import {FormControl} from "@angular/forms";
import {ReferenceTag} from "../../domain/values/reference-tag";

@Component({
  selector: 'app-wiki-page-body',
  template: `
      <div>
          <textarea
                  *ngIf="!referenceTag.isEmpty()"
                  [formControl]="bodyInput"
                  class="form-control"></textarea>
      </div>`
})
export class WikiPageBodyComponent implements OnChanges {

  @Input() referenceTag: ReferenceTag = ReferenceTag.empty();
  @Input() body: Body = Body.empty();
  bodyInput: FormControl;

  constructor() {
  }

  ngOnChanges(changes: SimpleChanges): void {
    this.bodyInput = new FormControl(this.body.body)
  }


}
