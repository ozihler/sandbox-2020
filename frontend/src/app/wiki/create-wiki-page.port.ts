import {Observable} from "rxjs";
import {WikiPage} from "./wiki-page";
import {Title} from "./wiki-page/domain/values/title";


export abstract class CreateWikiPageFromTitle {
  abstract from(title: Title): Observable<WikiPage>;
}
