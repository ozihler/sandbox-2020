import {Injectable} from '@angular/core';
import {UpdateWikiPageBody} from "../../../application.use_case/update-wiki-page-body.port";
import {Observable} from "rxjs";
import {WikiPage} from "../../../domain/wiki-page";
import {environment} from "../../../../../environments/environment";
import {WikiPageUpdateResponse} from "../create-wiki-page/wiki-page-update-response";
import {map} from "rxjs/operators";
import {HttpClient} from "@angular/common/http";
import {WikiPages} from "../../../application.use_case/wiki-pages";
import {CreateWikiPagesFromBodyRequest} from "./create-wiki-pages-from-body-request";
import {Body} from "../../../domain/body";

@Injectable({
  providedIn: 'root'
})
export class UpdateWikiPageBodyGateway implements UpdateWikiPageBody {

  private url: string = `${environment.baseUrl}/sandbox/wiki/pages/body`;

  constructor(private http: HttpClient) {
  }

  with(body: Body): Observable<WikiPage[]> {
    let request = new CreateWikiPagesFromBodyRequest(body);

    return this.http.post<WikiPageUpdateResponse>(this.url, request)
      .pipe(map(response => WikiPages.from(response)));
  }

}
