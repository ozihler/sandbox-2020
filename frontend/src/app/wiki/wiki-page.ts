import {WikiPageUpdateResponse} from "./wiki-page-update-response";

export class WikiPage {
  title: string;
  body: string;
  referenceTag: string;

  constructor(title: string, body: string, referenceTag: string) {
    this.title = title;
    this.body = body;
    this.referenceTag = referenceTag;
  }


  static from(response: WikiPageUpdateResponse): WikiPage {
    return new WikiPage(response.title, response.body, response.referenceTag);
  }

  static empty() {
    return new WikiPage("", "", "");
  }
}
