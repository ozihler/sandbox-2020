import {TestBed} from '@angular/core/testing';

import {FetchWikiPagesGateway} from './fetch-wiki-pages.service';

describe('FetchWikiPagesService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: FetchWikiPagesGateway = TestBed.get(FetchWikiPagesGateway);
    expect(service).toBeTruthy();
  });
});
