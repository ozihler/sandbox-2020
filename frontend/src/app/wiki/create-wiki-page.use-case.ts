import {CreateWikiPageFromTitleRequest} from "./create-wiki-page-from-title-request";
import {Observable} from "rxjs";
import {WikiPage} from "./wiki-page";


export abstract class CreateWikiPage {
  abstract fromTitle(wikiPageUpdateRequest: CreateWikiPageFromTitleRequest): Observable<WikiPage>;
}
