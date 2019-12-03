import {WikiPageUpdateResponse} from "./wiki-page-update-response";
import {Title} from "./wiki-page/domain/values/title";
import {Body} from "./wiki-page/domain/values/body";
import {ReferenceTag} from "./wiki-page/domain/values/reference-tag";

export class WikiPage {
  constructor(
    private _title: Title,
    private _body: Body,
    private _referenceTag: ReferenceTag) {
  }

  get title(): Title {
    return this._title;
  }

  get body(): Body {
    return this._body;
  }

  get referenceTag(): ReferenceTag {
    return this._referenceTag;
  }

  static from(response: WikiPageUpdateResponse): WikiPage {
    return new WikiPage(Title.from(response.title), Body.from(response.body), ReferenceTag.from(response.referenceTag));
  }

  static empty(): WikiPage {
    return new WikiPage(Title.empty(), Body.empty(), ReferenceTag.empty());
  }
}
