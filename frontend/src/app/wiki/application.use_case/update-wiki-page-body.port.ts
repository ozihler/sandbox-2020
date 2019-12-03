import {WikiPage} from "../domain/wiki-page";
import {Observable} from "rxjs";
import {Body} from "../domain/body";


export abstract class UpdateWikiPageBody {
  abstract with(body: Body): Observable<WikiPage[]>;
}
