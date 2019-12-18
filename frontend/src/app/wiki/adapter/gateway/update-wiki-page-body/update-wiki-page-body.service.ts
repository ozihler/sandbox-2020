import {Injectable} from '@angular/core';
import {UpdateWikiPageBody} from '../../../application.use_case/update-wiki-page-body.port';
import {Observable} from 'rxjs';
import {WikiPage} from '../../../domain/wiki-page';
import {environment} from '../../../../../environments/environment';
import {WikiPageDto} from '../create-wiki-page/wiki-page-dto';
import {map} from 'rxjs/operators';
import {HttpClient} from '@angular/common/http';
import {WikiPagesDto} from './wiki-pages-dto';

@Injectable({
  providedIn: 'root'
})
export class UpdateWikiPageBodyGateway implements UpdateWikiPageBody {

  private url = `${environment.baseUrl}/sandbox/wiki/pages/body`;

  constructor(private http: HttpClient) {
  }

  with(wikiPage: WikiPage): Observable<WikiPage> {
    const request = new WikiPagesDto(wikiPage);

    return this.http.post<WikiPageDto>(this.url, request)
      .pipe(map(response => WikiPage.from(response)));
  }

}
