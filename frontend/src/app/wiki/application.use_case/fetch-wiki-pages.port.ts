import {WikiPage} from "../domain/wiki-page";
import {Observable} from "rxjs";

export abstract class FetchWikiPages {
  abstract all(): Observable<WikiPage[]>;
}
