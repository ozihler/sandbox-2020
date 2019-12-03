import {WikiPage} from "../domain/wiki-page";

export class WikiPages {

  static from(response): WikiPage[] {
    let wikiPages: WikiPage[] = [];
    response.wikiPages.forEach(wikiPage => {
      wikiPages.push(WikiPage.from(wikiPage));
    });
    return wikiPages;
  }
}
