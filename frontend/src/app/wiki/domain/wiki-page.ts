import {WikiPageUpdateResponse} from "../adapter/gateway/create-wiki-page/wiki-page-update-response";
import {Title} from "./title";
import {Body} from "./body";
import {ReferenceTag} from "./reference-tag";

export class WikiPage {
  constructor(
    private _referenceTag: ReferenceTag,
    private _title: Title,
    private _body: Body) {
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

  static new(referenceTag: string, title: string, body: string): WikiPage {
    return new WikiPage(ReferenceTag.from(referenceTag), Title.from(title), Body.from(body));
  }

  static from(response: WikiPageUpdateResponse): WikiPage {
    return new WikiPage(ReferenceTag.from(response.referenceTag), Title.from(response.title), Body.from(response.body));
  }

  static empty(): WikiPage {
    return new WikiPage(ReferenceTag.empty(), Title.empty(), Body.empty());
  }
}
