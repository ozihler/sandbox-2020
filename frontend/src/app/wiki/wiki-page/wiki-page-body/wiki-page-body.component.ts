import {Component, Input, OnChanges, SimpleChanges} from '@angular/core';
import {Body} from "../domain/values/body";
import {FormControl} from "@angular/forms";
import {ReferenceTag} from "../domain/values/reference-tag";
import {debounceTime, distinctUntilChanged} from "rxjs/operators";
import {ReferenceTags} from "../domain/values/reference-tags";

@Component({
  selector: 'app-wiki-page-body',
  template: `
      <div>
          <textarea id="wiki-page-body"
                    class="form-control"
                    [formControl]="bodyInput"
                    *ngIf="!referenceTag.isEmpty()"></textarea>
      </div>`
})
export class WikiPageBodyComponent implements OnChanges {

  @Input() referenceTag: ReferenceTag;
  @Input() body: Body;
  bodyInput: FormControl;

  constructor() {
    this.referenceTag = ReferenceTag.empty();
    this.body = Body.empty();
  }

  ngOnChanges(changes: SimpleChanges): void {
    this.bodyInput = new FormControl(this.body.body);
    this.bodyInput.valueChanges.pipe(
      debounceTime(400),
      distinctUntilChanged()
    ).subscribe(res => {
      let referenceTags = this.findReferenceTags(res);
    });
  }

  private findReferenceTags(res: string): ReferenceTags {
    return null;
  }
}
