import {TestBed} from '@angular/core/testing';

import {UpdateWikiPageBodyGateway} from './update-wiki-page-body.service';

describe('UpdateWikiPageBodyService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: UpdateWikiPageBodyGateway = TestBed.get(UpdateWikiPageBodyGateway);
    expect(service).toBeTruthy();
  });
});
