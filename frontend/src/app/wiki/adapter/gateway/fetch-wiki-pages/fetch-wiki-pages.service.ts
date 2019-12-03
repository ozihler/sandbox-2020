import {Injectable} from '@angular/core';
import {FetchWikiPages} from "../../../application.use_case/fetch-wiki-pages.port";
import {Observable} from "rxjs";
import {WikiPage} from "../../../domain/wiki-page";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../../../environments/environment";
import {map} from "rxjs/operators";
import {WikiPages} from "../../../application.use_case/wiki-pages";

@Injectable({
  providedIn: 'root'
})
export class FetchWikiPagesGateway implements FetchWikiPages {
  private url: string = `${environment.baseUrl}/sandbox/wiki/pages`;

  constructor(private http: HttpClient) {
  }


  all(): Observable<WikiPage[]> {
    return this.http.get(this.url).pipe(map(response => WikiPages.from(response)));
  }
}
